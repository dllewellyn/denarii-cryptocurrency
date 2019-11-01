package com.denarii.uphold.models


import com.denarii.uphold.serializer.CryptoPairSerializer
import com.dllewellyn.denarii.models.currency.CryptoCurrency
import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.serializer.BigDecimalSerializer
import com.dllewellyn.denarii.serializer.SupportedCurrencySerializer
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Denomination(

    @SerialName("amount")
    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,

    @SerialName("currency")
    @Serializable(SupportedCurrencySerializer::class)
    val currency: SupportedCurrency,

    @SerialName("pair")
    @Serializable(CryptoPairSerializer::class)
    val pair: CurrencyPair,

    @SerialName("rate")
    @Serializable(BigDecimalSerializer::class)
    val rate: BigDecimal
)