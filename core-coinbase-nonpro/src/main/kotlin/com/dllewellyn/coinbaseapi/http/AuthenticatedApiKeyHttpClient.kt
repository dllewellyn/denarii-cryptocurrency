package com.dllewellyn.coinbaseapi.http

import com.dllewellyn.coinbaseapi.authentcation.SignatureGeneration
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import io.ktor.client.request.header
import io.ktor.client.utils.EmptyContent
import java.util.*

class AuthenticatedApiKeyHttpClient(
    private val apiKey: String,
    private val secretKey: String,
    internalUrl: String
) : InternalHttpClient(HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.BODY
    }
    defaultRequest {
        val timestamp = Calendar.getInstance(TimeZone.getTimeZone("UTC")).toInstant().epochSecond
        header(
            "CB-ACCESS-SIGN",
            SignatureGeneration(
                timestamp, method.value.toUpperCase(), url.buildString().removePrefix(internalUrl), if (body is EmptyContent) {
                    null
                } else {
                    body.toString()
                }, secretKey
            ).generate()
        )
        header("CB-ACCESS-KEY", apiKey)
        header("CB-ACCESS-TIMESTAMP", timestamp.toString())
    }
}, internalUrl)