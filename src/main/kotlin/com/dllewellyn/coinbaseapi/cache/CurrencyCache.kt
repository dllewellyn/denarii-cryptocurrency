package com.dllewellyn.coinbaseapi.cache

import com.dllewellyn.coinbaseapi.models.Currency

interface CurrencyCache {
    fun listOfCurrencies() : List<Currency>
    fun updateCache(newCache : List<Currency>)
}