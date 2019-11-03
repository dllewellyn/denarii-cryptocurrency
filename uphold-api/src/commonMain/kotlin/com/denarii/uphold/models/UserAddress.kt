package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class UserAddress(

    @SerialName("city")
    val city: String,

    @SerialName("line1")
    val line1: String,

    @SerialName("line2")
    val line2: String,

    @SerialName("zipCode")
    val zipCode: String
)