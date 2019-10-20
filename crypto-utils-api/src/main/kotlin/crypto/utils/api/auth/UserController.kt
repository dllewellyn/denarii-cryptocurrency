package crypto.utils.api.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import java.security.Principal
import javax.annotation.security.PermitAll
import javax.inject.Inject


data class User(val email: String, val password: String)

@Controller("/user")
class UserController {

    @Inject
    lateinit var firebase: FirebaseUtil

    @Secured("isAnonymous()")
    @Get("/myinfo")
    fun myinfo(principal: Principal?): Map<*, *> {
        return principal?.let {
            mapOf("isLoggedIn" to true, "username" to it.name)
        } ?: mapOf("isLoggedIn" to false)
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post("/create", consumes = [MediaType.APPLICATION_JSON])
    fun createUser(@Body user: User) =
        FirebaseAuth.getInstance()
            .createUser(
                UserRecord.CreateRequest()
                    .setEmail(user.email)
                    .setPassword(user.password)
            )
            .uid
}