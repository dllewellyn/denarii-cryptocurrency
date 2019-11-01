package com.dllewellyn.coinbaseapi.multiplatform.base

import com.dllewellyn.coinbaseapi.CryptoCurrencyDb
import com.dllewellyn.coinbaseapi.multiplatform.DatabaseInitialise
import com.dllewellyn.coinbaseapi.multiplatform.retrieveDatabase
import com.dllewellyn.denarii.callbacks.BaseRetriever

open class BaseDb<T> : BaseRetriever<T>() {

    init {
        DatabaseInitialise.initialise()
    }

    protected val database: CryptoCurrencyDb by lazy {
        CryptoCurrencyDb(retrieveDatabase())
    }
}