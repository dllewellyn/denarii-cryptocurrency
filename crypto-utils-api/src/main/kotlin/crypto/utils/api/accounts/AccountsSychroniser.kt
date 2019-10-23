package crypto.utils.api.accounts

import com.dllewellyn.coinbaseapi.OauthCoinbaseApi
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

@Controller("/wallet")
class AccountsSychroniser @Inject constructor(
    private val readOnlyRepository: ReadOnlyRepositoryArgument<String, OauthProvider>,
    private val writeOnlyRespository: WriteRepositoryArgument<String, List<Account>>
) {

    @Get("/synchronise")
    @Secured(IS_AUTHENTICATED)
    fun synchroniseWallet(principal: Principal) =
        runBlocking {
            val coinbase = readOnlyRepository.retrieveData(principal.name)
            CompositeRetriever<Account>().apply {
                retrievers.add(OauthCoinbaseApi(coinbase).coreAccounts())
            }.retrieveData()
                .let { writeOnlyRespository.write(it, principal.name) }
        }
}