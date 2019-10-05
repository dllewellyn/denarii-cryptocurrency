package com.dllewellyn.coinbaseapi.http

import io.ktor.client.HttpClient

open class InternalHttpClient(val httpClient: HttpClient, private val url: String = "https://api.coinbase.com/v2") {
    fun url(path: String) = "$url/$path"

}