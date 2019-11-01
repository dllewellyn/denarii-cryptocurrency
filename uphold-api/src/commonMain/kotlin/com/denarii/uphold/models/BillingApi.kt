package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BillingApi(
    @SerialName("name")
    val name: String
)