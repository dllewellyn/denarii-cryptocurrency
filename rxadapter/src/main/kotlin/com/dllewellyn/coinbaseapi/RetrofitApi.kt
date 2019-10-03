package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.websocket.internal.adapters.WebSocketAdapter


object RetrofitApi  {

    var sandbox = false
    private val webSocketAdapter : WebSocketAdapter by lazy {
        WebSocketAdapter(sandbox)
    }
    fun subscription() = webSocketAdapter
}

