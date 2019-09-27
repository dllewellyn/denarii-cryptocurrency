package com.dllewellyn.coinbaseapi.base

import com.dllewellyn.coinbaseapi.CoinbaseDatabase
import com.dllewellyn.coinbaseapi.DatabaseInitialise
import com.dllewellyn.coinbaseapi.callbacks.BaseRetriever
import com.dllewellyn.coinbaseapi.callbacks.Retriever
import com.dllewellyn.coinbaseapi.multiplatform.retrieveDatabase

open class BaseDb<T> : BaseRetriever<T>() {

    init {
        DatabaseInitialise.initialise()
    }

    protected val database: CoinbaseDatabase by lazy {
        CoinbaseDatabase(retrieveDatabase())
    }
}