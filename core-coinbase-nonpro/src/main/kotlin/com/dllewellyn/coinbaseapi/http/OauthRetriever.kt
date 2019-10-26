package com.dllewellyn.coinbaseapi.http

import com.dllewellyn.coinbaseapi.authentcation.OauthModel
import com.dllewellyn.coinbaseapi.authentcation.OauthSecretProvider
import com.dllewellyn.coinbaseapi.models.OauthProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable

@Serializable
data class ApiRefresh(
    val client_id: String,
    val client_secret: String,
    val refresh_token: String,
    val grant_type: String = "refresh_token"
)

class OauthRetriever(
    private val oauthSecretProvider: OauthSecretProvider
) : InternalHttpClient(HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.NONE
    }
}) {

    private val baseUrl = "https://api.coinbase.com"

    suspend fun retrieveCode(code: String): OauthProvider {
        val oauth = OauthModel(
            code = code,
            client_id = oauthSecretProvider.clientId,
            client_secret = oauthSecretProvider.secretKey,
            redirect_uri = oauthSecretProvider.redirectUri
        )

        return httpClient.post<HttpResponse>("$baseUrl/oauth/token") {
            body = TextContent(json.toJson(OauthModel.serializer(), oauth).toString(), ContentType.Application.Json)
        }
            .readText()
            .let {
                json.parse(OauthProvider.serializer(), it)
            }
    }

    suspend fun retrieveRefreshtoken(oauthProvider: OauthProvider): OauthProvider {
        val oauth = ApiRefresh(
            client_secret = oauthSecretProvider.secretKey,
            client_id = oauthSecretProvider.clientId,
            refresh_token = oauthProvider.refresh_token
        )

        return httpClient.post<HttpResponse>("$baseUrl/oauth/token") {
            body = TextContent(json.toJson(ApiRefresh.serializer(), oauth).toString(), ContentType.Application.Json)
        }
            .readText()
            .let {
                json.parse(OauthProvider.serializer(), it)
            }
    }
}