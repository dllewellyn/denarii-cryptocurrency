package crypto.utils.api.oauth

import com.dllewellyn.coinbaseapi.models.OauthProvider
import com.dllewellyn.coinbaseapi.repositories.WriteRepository
import com.google.gson.Gson
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule.IS_AUTHENTICATED
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import java.net.URI
import java.net.URLEncoder
import java.security.Principal
import java.util.*
import javax.annotation.security.PermitAll
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

@Controller("/oauth")
class CoinbaseOauthReceiver @Inject constructor(@Named("FirebaseCoinbaseStorage") private val repository: WriteRepository<OauthWrapper>) {

    @Value("\${coinbase.api.clientkey}")
    lateinit var clientKey: String

    @Value("\${coinbase.api.clientsecret}")
    lateinit var clientSecret: String

    @Value("\${coinbase.api.redirecturi}")
    lateinit var redirectUri: String

    @Inject
    @field:Client("https://api.coinbase.com")
    lateinit var client: RxHttpClient

    private val stateMap = mutableMapOf<String, String>()

    private val gson = Gson()

    val scopes: String = URLEncoder.encode(
        "wallet:addresses:read wallet:withdrawals:read wallet:user:read wallet:trades:read wallet:accounts:read wallet:orders:read",
        "UTF-8"
    )

    @Get("/callback")
    @PermitAll
    fun callback(@QueryValue(value = "code") code: String, @QueryValue(value = "state") state: String): Single<MutableHttpResponse<Any>> {
        val post = HttpRequest.POST(
            "/oauth/token",
            OauthModel(
                code = code,
                client_id = clientKey,
                client_secret = clientSecret,
                redirect_uri = redirectUri
            )
        ).contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE)


        return client.exchange(post, String::class.java)
            .map { it.body() }
            .toObservable()
            .firstOrError()
            .map { gson.fromJson(it, OauthProvider::class.java) }
            .map {
                with(it) {
                    stateMap[state]?.let {
                        val oauth = OauthWrapper(it, this)
                        runBlocking { repository.write(oauth) }
                        oauth.toString()
                    } ?: this.toString()
                }
            }
            .map { HttpResponse.redirect<Any>(URI("http://localhost:8080/user/myinfo")) }
    }

    @Get("/getUrl")
    @Secured(IS_AUTHENTICATED)
    fun buildUrl(principal: Principal): String =
        with(Base64.getUrlEncoder().encodeToString(randomString().toByteArray())) {
            stateMap[this] = principal.name
            "https://www.coinbase.com/oauth/authorize?client_id=$clientKey&redirect_uri=$redirectUri&response_type=code&scope=$scopes&state=$this&account=all&referral=llewel_b"
        }

    @Get("/connect")
    @Secured(IS_AUTHENTICATED)
    fun connect(principal: Principal) =
        HttpResponse.redirect<Any>(URI(buildUrl(principal)))

    private fun randomString(len: Int = 10) = with(StringBuilder()) {
        for (i in 0..len) {
            append(Random.nextInt(0, 127).toChar())
        }

        toString()
    }
}