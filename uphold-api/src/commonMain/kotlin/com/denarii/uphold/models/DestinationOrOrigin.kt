package com.denarii.uphold.models


import com.denarii.uphold.enums.CardType
import com.denarii.uphold.enums.CardTypeSerializer
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.serializer.BigDecimalSerializer
import com.dllewellyn.denarii.serializer.SupportedCurrencySerializer
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class DestinationOrOrigin(

    @SerialName("amount")
    @Serializable(BigDecimalSerializer::class)
    val amount: BigDecimal,

    @SerialName("base")
    @Serializable(BigDecimalSerializer::class)
    val base: BigDecimal,

    @SerialName("CardId")
    val cardId: String,

    @SerialName("commission")
    @Serializable(BigDecimalSerializer::class)
    val commission: BigDecimal,

    @SerialName("currency")
    @Serializable(SupportedCurrencySerializer::class)
    val currency: SupportedCurrency,

    @SerialName("description")
    val description: String,

    @SerialName("fee")
    @Serializable(BigDecimalSerializer::class)
    val fee: BigDecimal,

    @SerialName("isMember")
    val isMember: Boolean,

    @SerialName("node")
    val node: Node,

    @SerialName("rate")
    @Serializable(BigDecimalSerializer::class)
    val rate: BigDecimal,

    @SerialName("type")
    @Serializable(CardTypeSerializer::class)
    val type: CardType
)