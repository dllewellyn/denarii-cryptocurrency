package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Params(

    @SerialName("currency")
    val currency: String,

    @SerialName("margin")
    val margin: String,

    @SerialName("pair")
    val pair: String,

    @SerialName("progress")
    val progress: String,

    @SerialName("rate")
    val rate: String,

    @SerialName("ttl")
    val ttl: Int,

    @SerialName("type")
    val type: String
)