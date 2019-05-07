package com.dllewellyn.coinbaseapi.cache

import com.dllewellyn.coinbaseapi.models.Currency

class MemoryCurencyCache  : CurrencyCache {

    private var cache = listOf<Currency>()
    override fun listOfCurrencies() = cache
    override fun updateCache(newCache: List<Currency>) {
        cache = newCache
    }
}