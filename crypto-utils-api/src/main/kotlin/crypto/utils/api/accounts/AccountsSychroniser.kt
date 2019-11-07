package crypto.utils.api.accounts

import com.dllewellyn.coinbaseapi.CoinbaseProAuthenticatedApiImpl
import com.dllewellyn.coinbaseapi.OauthCoinbaseApi
import com.dllewellyn.coinbaseapi.api.models.ApiKeyAuth
import com.dllewellyn.coinbaseapi.authentcation.hasExpired
import com.dllewellyn.coinbaseapi.models.OauthProvider
import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument
import com.dllewellyn.denarii.repositories.WriteRepositorySingleArgument
import com.dllewellyn.denarii.repositories.WriteRepositoryArgument
import com.dllewellyn.denarii.retrievers.CompositeRetriever
import crypto.utils.api.oauth.CoinbaseSecretProvider
import crypto.utils.api.oauth.OauthWrapper
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule.IS_AUTHENTICATED
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list
import java.security.Principal
import javax.inject.Inject
import javax.inject.Named

@Controller("/wallet")
class AccountsSychroniser @Inject constructor(
    @Named("FirebaseAccountsStorage") private val writeOnlyRespository: WriteRepositoryArgument<String, List<Account>>,
    @Named("FirebaseCoinbaseProStorage") private val coinbaseProCredentials: ReadOnlyRepositoryArgument<String, ApiKeyAuth?>,
    @Named("FirebaseCoinbaseStorage") private val readOnlyRepository: ReadOnlyRepositoryArgument<String, OauthProvider?>,
    @Named("FirebaseCoinbaseStorage") private val repository: WriteRepositorySingleArgument<OauthWrapper>,
    private val oauthSecretProvider: CoinbaseSecretProvider
) {

    @UseExperimental(ImplicitReflectionSerializer::class)
    @Get("/synchronise")
    @Secured(IS_AUTHENTICATED)
    fun synchroniseWallet(principal: Principal) =
        runBlocking {
            retreieveData(principal).also {
                writeOnlyRespository.write(it, principal.name)
            }
        }

    @Get("/getWallets")
    @Secured(IS_AUTHENTICATED)
    fun getWallets(principal: Principal) =
        runBlocking {
            retreieveData(principal).let {
                val json = kotlinx.serialization.json.Json(JsonConfiguration.Stable.copy(strictMode = false))
                json.toJson(Account.serializer().list, it).toString()
            }
        }

    private suspend fun retreieveData(principal: Principal): List<Account> {

        val coinbase = readOnlyRepository.retrieveData(principal.name)?.let { coinbase ->
            if (coinbase.hasExpired()) {
                oauthSecretProvider.provideOauthRetriever()
                    .retrieveRefreshtoken(coinbase)
                    .also {
                        repository.write(
                            OauthWrapper(
                                principal.name,
                                it
                            )
                        )
                    }
            } else {
                coinbase
            }
        }



        return CompositeRetriever<Account>().apply {

            coinbase?.let {
                val coreAccounts = OauthCoinbaseApi(it)
                retrievers.add(coreAccounts.coreAccounts())

            }
            coinbaseProCredentials.retrieveData(principal.name)?.let {
                retrievers.add(CoinbaseProAuthenticatedApiImpl(it).accounts())
            }
        }.retrieveData()
    }

}