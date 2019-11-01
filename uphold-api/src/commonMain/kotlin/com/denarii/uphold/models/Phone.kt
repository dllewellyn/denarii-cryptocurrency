package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Phone(

    @SerialName("e164Masked")
    val e164Masked: String,

    @SerialName("id")
    val id: String,

    @SerialName("internationalMasked")
    val internationalMasked: String,

    @SerialName("nationalMasked")
    val nationalMasked: String,

    @SerialName("primary")
    val primary: Boolean,

    @SerialName("verified")
    val verified: Boolean
)