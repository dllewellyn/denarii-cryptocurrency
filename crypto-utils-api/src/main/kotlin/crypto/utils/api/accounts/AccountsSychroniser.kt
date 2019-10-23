package crypto.utils.api.accounts

import com.dllewellyn.coinbaseapi.AuthenticatedApiImpl
import com.dllewellyn.coinbaseapi.OauthCoinbaseApi
import com.dllewellyn.coinbaseapi.api.models.ApiKeyAuth
import com.dllewellyn.coinbaseapi.authenticated_builder
import com.dllewellyn.coinbaseapi.models.Account
import com.dllewellyn.coinbaseapi.models.OauthProvider
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepositoryArgument
import com.dllewellyn.coinbaseapi.repositories.WriteRepository
import com.dllewellyn.coinbaseapi.repositories.WriteRepositoryArgument
import com.dllewellyn.coinbaseapi.retrievers.CompositeRetriever
import crypto.utils.api.oauth.OauthModel
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
    private val writeOnlyRespository: WriteRepositoryArgument<String, List<Account>>,
    @Named("FirebaseCoinbaseProStorage") private val coinbaseProCredentials: ReadOnlyRepositoryArgument<String, ApiKeyAuth?>,
    @Named("FirebaseUserStorage") private val readOnlyRepository: ReadOnlyRepositoryArgument<String, OauthProvider>
) {

    @Get("/synchronise")
    @Secured(IS_AUTHENTICATED)
    fun synchroniseWallet(principal: Principal) =
        runBlocking {
            val coinbase = readOnlyRepository.retrieveData(principal.name)


            CompositeRetriever<Account>().apply {
                retrievers.add(OauthCoinbaseApi(coinbase).coreAccounts())
                coinbaseProCredentials.retrieveData(principal.name)?.let {
                    retrievers.add(AuthenticatedApiImpl(it).accounts())
                }
            }.retrieveData()
                .let { writeOnlyRespository.write(it, principal.name) }
        }
}