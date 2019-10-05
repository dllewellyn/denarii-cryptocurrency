package com.dllewellyn.coinbaseapi.adapters

import io.ktor.client.HttpClient

class InternalHttpClient(val httpClient: HttpClient, private val url: String = "https://api.coinbase.com/v2") {

    fun url(path: String) = "$url/$path"
}