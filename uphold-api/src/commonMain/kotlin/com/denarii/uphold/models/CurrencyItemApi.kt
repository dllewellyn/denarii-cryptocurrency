package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class CurrencyItemApi(
    @Optional
    @SerialName("amount")
    val amount: String,
    @Optional
    @SerialName("balance")
    val balance: String,
    @Optional
    @SerialName("currency")
    val currency: String,
    @Optional
    @SerialName("rate")
    val rate: String
)