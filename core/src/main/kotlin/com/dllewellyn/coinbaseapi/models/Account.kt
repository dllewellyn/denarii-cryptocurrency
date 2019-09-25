package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import java.math.BigInteger

data class Account(
    val currencyValue: SupportedCurrency,
    val balance: BigInteger,
    val available: BigInteger,
    val hold: BigInteger,
    val uid: String
)
