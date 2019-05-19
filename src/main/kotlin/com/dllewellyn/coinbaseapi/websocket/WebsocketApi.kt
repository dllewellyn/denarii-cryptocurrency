package com.dllewellyn.coinbaseapi.websocket

import com.dllewellyn.coinbaseapi.websocket.internal.listener.RawListener
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit


class WebsocketApi(private val url : String) {

    val handler = RawListener()

    private fun okHttp() = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    fun sockets() = okHttp()
        .newWebSocket(
            Request.Builder().url(url).build(),
            handler
        )


}