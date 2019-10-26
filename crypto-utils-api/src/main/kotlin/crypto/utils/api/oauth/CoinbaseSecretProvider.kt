package crypto.utils.api.oauth

import com.dllewellyn.coinbaseapi.authentcation.OauthSecretProvider
import com.dllewellyn.coinbaseapi.http.OauthRetriever
import io.micronaut.context.annotation.Value
import javax.inject.Singleton

@Singleton
class CoinbaseSecretProvider {

    @Value("\${coinbase.api.clientkey}")
    lateinit var clientKey: String

    @Value("\${coinbase.api.clientsecret}")
    lateinit var clientSecret: String

    @Value("\${coinbase.api.redirecturi}")
    lateinit var redirectUri: String

    fun clientConfiguration() = OauthSecretProvider(
        redirectUri,
        clientSecret,
        clientKey
    )

    fun provideOauthRetriever() = OauthRetriever(clientConfiguration())
}