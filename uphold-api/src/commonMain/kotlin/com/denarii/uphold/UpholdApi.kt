package com.denarii.uphold

import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

expect fun retrieveHttpClient() : HttpClient

val json = Json(JsonConfiguration.Stable.copy(strictMode = false))

open class UpholdApi {

    private val client = retrieveHttpClient()

}