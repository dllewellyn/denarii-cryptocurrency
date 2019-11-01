package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class DateTimeFormat(

    @SerialName("locale")
    val locale: String
)