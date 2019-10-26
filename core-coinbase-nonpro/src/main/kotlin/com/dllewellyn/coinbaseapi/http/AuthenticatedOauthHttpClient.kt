package com.dllewellyn.coinbaseapi.http

import com.dllewellyn.coinbaseapi.models.OauthProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.header

class AuthenticatedOauthHttpClient(
    private val oauthProvider: OauthProvider
) : InternalHttpClient(HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.NONE
    }
    defaultRequest {
        header("Authorization", "Bearer ${oauthProvider.access_token}")
    }
})