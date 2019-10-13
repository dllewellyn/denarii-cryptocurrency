package com.dllewellyn.coinbaseapi.multiplatform.base

import com.dllewellyn.coinbaseapi.CryptoCurrencyDb
import com.dllewellyn.coinbaseapi.DatabaseInitialise
import com.dllewellyn.coinbaseapi.callbacks.BaseRetriever
import com.dllewellyn.coinbaseapi.multiplatform.retrieveDatabase

open class BaseDb<T> : BaseRetriever<T>() {

    init {
        DatabaseInitialise.initialise()
    }

    protected val database: CryptoCurrencyDb by lazy {
        CryptoCurrencyDb(retrieveDatabase())
    }
}