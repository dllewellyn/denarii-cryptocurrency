package crypto.utils.api.oauth

import com.dllewellyn.coinbaseapi.models.OauthProvider
import com.dllewellyn.coinbaseapi.repositories.WriteRepository
import com.google.gson.Gson
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule.IS_AUTHENTICATED
import kotlinx.coroutines.runBlocking
import java.net.URI
import java.security.Principal
import java.util.*
import javax.annotation.security.PermitAll
import javax.inject.Inject
import kotlin.random.Random

data class OauthModel(
    val grant_type: String = "authorization_code",
    val code: String,
    val client_id: String,
    val client_secret: String,
    val redirect_uri: String
)

@Controller("/oauth")
class CoinbaseOauthReceiver @Inject constructor(private val repository: WriteRepository<OauthWrapper>) {

    @Value("\${coinbase.api.clientkey}")
    lateinit var clientKey: String

    @Value("\${coinbase.api.clientsecret}")
    lateinit var clientSecret: String

    @Value("\${coinbase.api.redirecturi}")
    lateinit var redirectUri: String

    @Inject
    @field:Client("https://api.coinbase.com")
    lateinit var client: HttpClient

    private val stateMap = mutableMapOf<String, String>()

    @Get("/callback")
    @PermitAll
    fun callback(@QueryValue(value = "code") code: String, @QueryValue(value = "state") state: String): String {
        val post = HttpRequest.POST(
            "/oauth/token",
            OauthModel(
                code = code,
                client_id = clientKey,
                client_secret = clientSecret,
                redirect_uri = redirectUri
            )
        ).contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE)

        val body = client.toBlocking().exchange(post, String::class.java).body()
        val baseModel = Gson().fromJson(body, OauthProvider::class.java)
        return with(baseModel) {
            stateMap[state]?.let {
                val oauth = OauthWrapper(it, this)
                runBlocking { repository.write(oauth) }
                oauth.toString()
            } ?: this.toString()
        }
    }

    @Get("/getUrl")
    @Secured(IS_AUTHENTICATED)
    fun buildUrl(principal: Principal) : String = with(Base64.getUrlEncoder().encodeToString(randomString().toByteArray())) {
        stateMap[this] = principal.name
        "https://www.coinbase.com/oauth/authorize?client_id=$clientKey&redirect_uri=$redirectUri&response_type=code&scope=wallet%3Auser%3Aread&state=$this"
    }

    @Get("/connect")
    @PermitAll
    fun connect(principal: Principal?) =
        HttpResponse.redirect<Any>(URI("https://www.coinbase.com/oauth/authorize?client_id=$clientKey&redirect_uri=$redirectUri&response_type=code&scope=wallet%3Auser%3Aread"))

    private fun randomString(len: Int = 10) = with(StringBuilder()) {
        for (i in 0..len) {
            append(Random.nextInt(0, 127).toChar())
        }

        toString()
    }
}