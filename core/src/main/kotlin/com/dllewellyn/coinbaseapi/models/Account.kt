package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency

data class Account(
    val currencyValue: SupportedCurrency,
    val balance : Double,
    val available : Double,
    val hold : Double,
    val uid : String
)
