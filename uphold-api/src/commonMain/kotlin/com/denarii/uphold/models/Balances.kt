package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Balances(

    @SerialName("currencies")
    val currencies: Map<String, CurrencyItemApi>,

    @SerialName("total")
    val total: String
)