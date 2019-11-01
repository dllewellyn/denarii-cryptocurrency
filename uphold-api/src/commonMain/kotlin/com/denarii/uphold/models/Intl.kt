package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Intl(

    @SerialName("dateTimeFormat")
    val dateTimeFormat: DateTimeFormat,

    @SerialName("language")
    val language: Language,

    @SerialName("numberFormat")
    val numberFormat: NumberFormat
)