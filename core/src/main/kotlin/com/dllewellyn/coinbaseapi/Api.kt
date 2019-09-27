package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.callbacks.GenericCallback
import com.dllewellyn.coinbaseapi.interfaces.CurrencyPairsList
import com.dllewellyn.coinbaseapi.interfaces.CurrencyPrice
import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency

interface Api {
    fun currencies(): ReadOnlyRepository<List<SupportedCurrency>>
    fun exchangeRates(): ExchangeRateRetriver
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