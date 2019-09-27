package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.multiplatform.retrieveDatabase

object DatabaseInitialise {
    private var initialiseOnce = false

    fun initialise() {
        if (initialiseOnce.not()) {
            CoinbaseDatabase.Schema.create(retrieveDatabase())
            initialiseOnce = true
        }
    }
}