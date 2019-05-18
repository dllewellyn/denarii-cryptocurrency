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

class WebSocketAdapter : EventSubscription {

    private val websocketApi: WebsocketApi by lazy {
        WebsocketApi()
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


    override fun unsubscribeToEvent(vararg pair: CurrencyPair, channel: Channel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}