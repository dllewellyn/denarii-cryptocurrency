package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class SettingsX(

    @SerialName("currency")
    val currency: String,

    @SerialName("hasMarketingConsent")
    val hasMarketingConsent: Boolean,

    @SerialName("hasNewsSubscription")
    val hasNewsSubscription: Boolean,

    @SerialName("intl")
    val intl: Intl,

    @SerialName("otp")
    val otp: Otp
)