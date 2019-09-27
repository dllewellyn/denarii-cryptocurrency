package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.callbacks.GenericCallback
import com.dllewellyn.coinbaseapi.interfaces.*
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.marketinfo.TwentyFourHourStats
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import com.dllewellyn.coinbaseapi.repositories.WriteRepository

interface Api {
    fun currencies() : CurrencyList
    fun exchangeRates(): ExchangeRateRetriver
    fun productTicker() : ProductTickerRetriever
    fun twentyFourHours() : TwentyFourHourStatsRetriever
    fun currencyPairs(): CurrencyPairsList
    fun buyAndSellPrices(): CurrencyPrice

}

class Configurator<T> {

    private val callbacks = mutableListOf<GenericCallback<T>>()

    var localCache: ReadOnlyRepository<T?>? = null
    var localCacheWriter: WriteRepository<T>? = null
    var endpoint: ReadOnlyRepository<T>? = null

    fun callback(t: GenericCallback<T>) {
        callbacks.add(t)
    }
}