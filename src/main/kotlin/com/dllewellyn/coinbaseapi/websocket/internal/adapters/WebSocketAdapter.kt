package com.dllewellyn.coinbaseapi.websocket.internal.adapters

import com.dllewellyn.coinbaseapi.interfaces.EventSubscription
import com.dllewellyn.coinbaseapi.models.Channel
import com.dllewellyn.coinbaseapi.models.CurrencyPair
import com.dllewellyn.coinbaseapi.models.EventResponse
import com.dllewellyn.coinbaseapi.websocket.WebsocketApi
import com.dllewellyn.coinbaseapi.websocket.internal.models.SubscribeApi
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.WebSocket

class WebSocketAdapter(sandbox: Boolean) : EventSubscription {

    private val url : String = if (sandbox) {
        "wss://ws-feed-public.sandbox.pro.coinbase.com"
    } else {
        "wss://ws-feed.pro.coinbase.com"
    }

    private val websocketApi: WebsocketApi by lazy {
        WebsocketApi(url)
    }

    private val sockets: WebSocket by lazy {
        websocketApi.sockets()
    }

    private val gson: Gson by lazy {
        Gson()
    }

    override fun subscribeToEvent(channel: List<Channel>, vararg pair: CurrencyPair): Observable<EventResponse> {

        sockets.send(gson.toJson(SubscribeApi(
            product_ids = pair.map { "${it.baseCurrency}-${it.quoteCurrency}" },
            channels = channel.map { it.type }
        )))

        return websocketApi.handler.event.filter {
            when (it) {
                is EventResponse.Level2Update -> pair.filter { filter ->
                    it.buyAndSell.currencyFrom == filter.baseCurrency && it.buyAndSell.currencyTo == filter.quoteCurrency
                }.isNotEmpty()

                is EventResponse.Level2Snapshot -> pair.filter { filter ->
                    it.buyAndSell.currencyFrom == filter.baseCurrency && it.buyAndSell.currencyTo == filter.quoteCurrency
                }.isNotEmpty()
            }
        }
    }


    override fun unsubscribeToEvent(channel: List<Channel>, vararg pair: CurrencyPair) {
        sockets.send(gson.toJson(SubscribeApi(
            type = "unsubscribe",
            product_ids = pair.map { "${it.baseCurrency}-${it.quoteCurrency}" },
            channels = channel.map { it.type }
        )))
    }

}