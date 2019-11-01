package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Source(

    @SerialName("amount")
    val amount: String,

    @SerialName("id")
    val id: String
)