package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.websocket.internal.adapters.WebSocketAdapter

class AuthenticatedApiImpl(
    private val sandbox: Boolean
) {

    private val webSocketAdapter: WebSocketAdapter by lazy {
        WebSocketAdapter(sandbox)
    }

}
