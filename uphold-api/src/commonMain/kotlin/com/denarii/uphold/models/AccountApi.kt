package com.denarii.uphold.models


import com.denarii.uphold.enums.CardType
import com.denarii.uphold.enums.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountApi(
    @SerialName("billing")
    val billing: BillingApi,
    @SerialName("brand")
    val brand: String,
    @SerialName("currency")
    val currency: String,
    @SerialName("id")
    val id: String,
    @SerialName("label")
    val label: String,
    @SerialName("status")
    val status: Status,
    @SerialName("type")
    val type: CardType
)