package com.dllewellyn.coinbaseapi

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

fun main() {
    val client = HttpClient(CIO)

}