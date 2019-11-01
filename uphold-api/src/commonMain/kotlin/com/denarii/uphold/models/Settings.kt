package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Settings(

    @SerialName("position")
    val position: Int,

    @SerialName("protected")
    val `protected`: Boolean,

    @SerialName("starred")
    val starred: Boolean
)