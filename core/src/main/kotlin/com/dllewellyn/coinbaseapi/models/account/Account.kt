package com.dllewellyn.coinbaseapi.models.account

import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import java.math.BigDecimal

data class Account(
    val currencyValue: SupportedCurrency,
    val balance: BigDecimal,
    val available: BigDecimal?=null,
    val hold: BigDecimal?=null,
    val uid: String,
    val provider : String,
    val transactions: List<Transaction> = listOf()
)
