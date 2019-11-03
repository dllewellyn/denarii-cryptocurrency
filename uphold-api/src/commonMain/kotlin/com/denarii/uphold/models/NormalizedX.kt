package com.denarii.uphold.models


import com.denarii.uphold.enums.ApiTarget
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.serializer.BigDecimalSerializer
import com.dllewellyn.denarii.serializer.SupportedCurrencySerializer
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializer

@Serializable
data class NormalizedX(

    @SerialName("amount")
    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,

    @SerialName("commission")
    @Serializable(BigDecimalSerializer::class)
    val commission: BigDecimal,

    @SerialName("currency")
    @Serializable(SupportedCurrencySerializer::class)
    val currency: SupportedCurrency,

    @SerialName("fee")
    @Serializable(BigDecimalSerializer::class)
    val fee: BigDecimal,

    @SerialName("rate")
    @Serializable(BigDecimalSerializer::class)
    val rate: BigDecimal,

    @SerialName("target")
    val target: ApiTarget
)