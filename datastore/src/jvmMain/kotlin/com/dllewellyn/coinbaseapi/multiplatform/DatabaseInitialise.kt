package com.dllewellyn.coinbaseapi.multiplatform

import com.dllewellyn.coinbaseapi.CryptoCurrencyDb

object DatabaseInitialise {
    private var initialiseOnce = false

    fun initialise() {
        if (initialiseOnce.not()) {
            CryptoCurrencyDb.Schema.create(retrieveDatabase())
            initialiseOnce = true
        }
    }
}