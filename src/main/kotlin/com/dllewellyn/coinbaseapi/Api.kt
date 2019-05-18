package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.interfaces.*
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder
import com.dllewellyn.coinbaseapi.retrofit.adapters.CurrencyListAdapter
import com.dllewellyn.coinbaseapi.retrofit.adapters.CurrencyPairAdapter
import com.dllewellyn.coinbaseapi.retrofit.adapters.CurrencyPriceAdapter
import com.dllewellyn.coinbaseapi.retrofit.adapters.ExchangeRateRetriverAdapter
import com.dllewellyn.coinbaseapi.websocket.WebsocketApi
import com.dllewellyn.coinbaseapi.websocket.internal.adapters.WebSocketAdapter

object Api {

    private val retrofit : RetrofitApiBuilder by lazy {
        RetrofitApiBuilder()
    }

    private val webSocketAdapter : WebSocketAdapter by lazy {
        WebSocketAdapter()
    }

    fun currencies() : CurrencyList = CurrencyListAdapter(retrofit)
    fun exchangeRates() : ExchangeRateRetriver = ExchangeRateRetriverAdapter(retrofit)
    fun currencyPairs() : CurrencyPairsList = CurrencyPairAdapter(retrofit)
    fun buyAndSellPrices() : CurrencyPrice = CurrencyPriceAdapter(retrofit)

    fun subscription() = webSocketAdapter
}