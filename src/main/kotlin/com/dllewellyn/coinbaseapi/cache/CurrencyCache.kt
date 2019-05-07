package com.dllewellyn.coinbaseapi.cache

import com.dllewellyn.coinbaseapi.models.SupportedCurrency

interface CurrencyCache {
    fun listOfCurrencies() : List<SupportedCurrency>
    fun updateCache(newCache : List<SupportedCurrency>)
}