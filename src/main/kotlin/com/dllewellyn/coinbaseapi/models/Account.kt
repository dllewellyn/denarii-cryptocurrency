package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency

data class Account(
    val currencyValue: SupportedCurrency,
    val balance : Float,
    val available : Float,
    val hold : Float,
    val uid : String
)
