package com.dllewellyn.coinbaseapi.models.account

import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.serializer.BigDecimalSerializer
import com.dllewellyn.denarii.serializer.SupportedCurrencySerializer
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.Serializable

@Serializable
data class Account(
    @Serializable(SupportedCurrencySerializer::class)
    val currencyValue: SupportedCurrency,
    @Serializable(BigDecimalSerializer::class)
    val balance: BigDecimal,
    @Serializable(BigDecimalSerializer::class)
    val available: BigDecimal? = null,
    @Serializable(BigDecimalSerializer::class)
    val hold: BigDecimal? = null,
    val uid: String,
    val provider: String,
    val transactions: List<Transaction> = listOf(),
    @Serializable(BigDecimalSerializer::class)
    val dollarValue: BigDecimal? = null
)
