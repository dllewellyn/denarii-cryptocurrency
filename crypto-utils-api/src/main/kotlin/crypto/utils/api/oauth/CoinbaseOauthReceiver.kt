package crypto.utils.api.oauth

import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpRequest
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import javax.inject.Inject

data class OauthModel(
    val grant_type: String = "authorization_code",
    val code: String,
    val client_id: String,
    val client_secret: String,
    val redirect_uri: String
)

@Controller("/oauth")
class CoinbaseOauthReceiver {

    @Value("\${coinbase.api.client-key}")
    lateinit var clientKey: String

    @Value("\${coinbase.api.client-secret}")
    lateinit var clientSecret: String

    @Value("\${coinbase.api.redirect-uri}")
    lateinit var redirectUri: String

    @Inject
    @field:Client("https://api.coinbase.com")
    lateinit var client: HttpClient

    @Get("/callback")
    fun callback(@QueryValue(value = "code") code: String): String? {
        val post = HttpRequest.POST(
            "/oauth/token",
            OauthModel(
                code = code,
                client_id = clientKey,
                client_secret = clientSecret,
                redirect_uri = redirectUri
            )
        ).contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE)

        return client.toBlocking().exchange(post, String::class.java).body()
    }

}