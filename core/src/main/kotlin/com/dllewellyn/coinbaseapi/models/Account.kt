package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import java.math.BigDecimal

data class Account(
    val currencyValue: SupportedCurrency,
    val balance: BigDecimal,
    val available: BigDecimal,
    val hold: BigDecimal,
    val uid: String
)
