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
import io.swagger.v3.oas.annotations.Operation
import java.security.Principal

data class User(val email: String, val password: String)
data class DataStorage(val key : String)

@Controller("/user")
class UserController {

    @Operation(summary = "Retrieve users information", operationId = "myinfo", description = "This can only be done by the logged in user. Get user info", tags=["user"])
    @Secured("isAnonymous()")
    @Get("/myinfo")
    fun myinfo(principal: Principal?): Map<*, *> {
        return principal?.let {
            mapOf("isLoggedIn" to true, "username" to it.name)
        } ?: mapOf("isLoggedIn" to false)
    }

    @Operation(summary = "Create a user", operationId = "createUser", description = "Create an account", tags=["anonymous"])
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


    @Operation(summary = "Store data for a user", operationId = "storeData", description = "Store user data", tags=["user"])
    @Post("/store")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    fun storeData(@Body storage : DataStorage, principal: Principal) = FirebaseUtil
        .fireStore()
        .collection("users")
        .document(principal.name)
        .set(storage)
}