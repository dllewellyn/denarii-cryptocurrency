package com.dllewellyn.coinbaseapi.cache

class MemoryCache<T>  : Cache<T> {

    private var cache = listOf<T>()
    override fun listOfCurrencies() = cache
    override fun updateCache(newCache: List<T>) {
        cache = newCache
    }
}