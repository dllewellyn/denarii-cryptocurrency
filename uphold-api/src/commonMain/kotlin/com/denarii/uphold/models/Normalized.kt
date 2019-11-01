package com.denarii.uphold.models


import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.serializer.BigDecimalSerializer
import com.dllewellyn.denarii.serializer.SupportedCurrencySerializer
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializer

@Serializable
data class Normalized(

    @SerialName("available")
    @Serializable(BigDecimalSerializer::class)
    val available: BigDecimal,

    @SerialName("balance")
    @Serializable(BigDecimalSerializer::class)
    val balance: BigDecimal,

    @SerialName("currency")
    @Serializable(SupportedCurrencySerializer::class)
    val currency: SupportedCurrency
)