package crypto.utils.api.oauth

import com.dllewellyn.coinbaseapi.api.models.ApiKeyAuth
import com.dllewellyn.coinbaseapi.repositories.WriteRepositoryArgument
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule.IS_AUTHENTICATED
import kotlinx.coroutines.runBlocking
import java.security.Principal
import javax.inject.Inject
import javax.inject.Named

@Controller("coinbasepro")
class CoinbaseProReceiver @Inject constructor(@Named("FirebaseCoinbaseProStorage") val writer: WriteRepositoryArgument<String, ApiKeyAuth>) {

    @Post("credentials")
    @Secured(IS_AUTHENTICATED)
    fun credentials(principal: Principal, @Body proAuth: ApiKeyAuth) = runBlocking {
        writer.write(proAuth, principal.name)
    }
}