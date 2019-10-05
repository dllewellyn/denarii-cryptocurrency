package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.adapters.CurrencyListAdapter
import com.dllewellyn.coinbaseapi.adapters.ExchangeRateRetriverAdapter
import com.dllewellyn.coinbaseapi.adapters.InternalHttpClient
import com.dllewellyn.coinbaseapi.adapters.PricesAdapter
import com.dllewellyn.coinbaseapi.interfaces.CurrencyList
import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
import com.dllewellyn.coinbaseapi.interfaces.Prices
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature

class CoinbaseApi {

    private val client = HttpClient(CIO) {
        install(JsonFeature)
    }

    companion object {
        const val url = "https://api.coinbase.com/v2/"
    }

    private val httpClient = InternalHttpClient(client, url)

    fun exchangeRateRetriever(): ExchangeRateRetriver =
        ExchangeRateRetriverAdapter(httpClient)

    fun currencyList(): CurrencyList =
        CurrencyListAdapter(httpClient)

    fun prices() : Prices = PricesAdapter(httpClient)

}