package com.denarii.uphold.models


import com.dllewellyn.denarii.serializer.BigDecimalSerializer
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardApi(

    @SerialName("address")
    val address: Address,

    @SerialName("available")
    @Serializable(with = BigDecimalSerializer::class)
    val available: BigDecimal,

    @SerialName("balance")
    @Serializable(with = BigDecimalSerializer::class)
    val balance: BigDecimal,

    @SerialName("currency")
    val currency: String,

    @SerialName("id")
    val id: String,

    @SerialName("label")
    val label: String,

    @SerialName("lastTransactionAt")
    val lastTransactionAt: String,

    @SerialName("normalized")
    val normalized: List<Normalized>,

    @SerialName("settings")
    val settings: Settings
)