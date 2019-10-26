package com.dllewellyn.coinbaseapi.http

import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

open class InternalHttpClient(val httpClient: HttpClient, private val url: String = "https://api.coinbase.com/v2") {
    fun url(path: String) = "$url/$path"

    val json = Json(JsonConfiguration.Stable.copy(strictMode = false))
}