package com.dllewellyn.coinbaseapi.models

data class Account(
    val currencyValue: SupportedCurrency,
    val balance : Float,
    val available : Float,
    val hold : Float,
    val uid : String
)
