package com.dllewellyn.coinbaseapi.cache

import io.reactivex.Single

interface Cache<T> {
    fun listOfCurrencies() : List<T>
    fun updateCache(newCache : List<T>)
}

fun <T>Single<List<T>>.intoCache(cache : Cache<T>) =
    doAfterSuccess { cache.updateCache(it) }

