package crypto.utils.api.accounts

import com.dllewellyn.coinbaseapi.AuthenticatedApiImpl
import com.dllewellyn.coinbaseapi.OauthCoinbaseApi
import com.dllewellyn.coinbaseapi.api.models.ApiKeyAuth
import com.dllewellyn.coinbaseapi.authentcation.hasExpired
import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.coinbaseapi.models.OauthProvider
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepositoryArgument
import com.dllewellyn.coinbaseapi.repositories.WriteRepositoryArgument
import com.dllewellyn.coinbaseapi.retrievers.CompositeRetriever
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule.IS_AUTHENTICATED
import kotlinx.coroutines.runBlocking
import java.security.Principal
import javax.inject.Inject
import javax.inject.Named

@Controller("/wallet")
class AccountsSychroniser @Inject constructor(
    @Named("FirebaseAccountsStorage") private val writeOnlyRespository: WriteRepositoryArgument<String, List<Account>>,
    @Named("FirebaseCoinbaseProStorage") private val coinbaseProCredentials: ReadOnlyRepositoryArgument<String, ApiKeyAuth?>,
    @Named("FirebaseCoinbaseStorage") private val readOnlyRepository: ReadOnlyRepositoryArgument<String, OauthProvider>
) {

    @Get("/synchronise")
    @Secured(IS_AUTHENTICATED)
    fun synchroniseWallet(principal: Principal) =
        runBlocking {
            val coinbase = readOnlyRepository.retrieveData(principal.name)

            if (coinbase.hasExpired()) {

            }

            CompositeRetriever<Account>().apply {

                val coreAccounts = OauthCoinbaseApi(coinbase)

                retrievers.add(coreAccounts.coreAccounts())
                coinbaseProCredentials.retrieveData(principal.name)?.let {
                    retrievers.add(AuthenticatedApiImpl(it).accounts())
                }
            }.retrieveData()
                .let { writeOnlyRespository.write(it, principal.name) }
        }
}