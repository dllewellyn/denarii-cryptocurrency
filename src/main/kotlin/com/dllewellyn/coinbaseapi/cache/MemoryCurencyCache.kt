package com.dllewellyn.coinbaseapi.cache

import com.dllewellyn.coinbaseapi.models.SupportedCurrency

class MemoryCurencyCache  : CurrencyCache {

    private var cache = listOf<SupportedCurrency>()
    override fun listOfCurrencies() = cache
    override fun updateCache(newCache: List<SupportedCurrency>) {
        cache = newCache
    }
}