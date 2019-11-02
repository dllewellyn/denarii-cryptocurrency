package com.dllewellyn.denarii.base

import com.dllewellyn.coinbaseapi.CryptoCurrencyDb
import com.dllewellyn.denarii.callbacks.BaseRetriever
import com.squareup.sqldelight.db.SqlDriver

open class BaseDb<T>(private val sqlDriver: SqlDriver) : BaseRetriever<T>() {

    init {
        DatabaseInitialise.initialise(sqlDriver)
    }

    protected val database: CryptoCurrencyDb by lazy {
        CryptoCurrencyDb(sqlDriver)
    }
}