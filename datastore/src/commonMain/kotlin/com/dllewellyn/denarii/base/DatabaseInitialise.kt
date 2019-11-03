package com.dllewellyn.denarii.base

import com.dllewellyn.coinbaseapi.CryptoCurrencyDb
import com.squareup.sqldelight.db.SqlDriver

object DatabaseInitialise {
    private var initialiseOnce = false

    fun initialise(sqlDriver: SqlDriver) {
        if (initialiseOnce.not()) {
            CryptoCurrencyDb.Schema.create(sqlDriver)
            initialiseOnce = true
        }
    }
}