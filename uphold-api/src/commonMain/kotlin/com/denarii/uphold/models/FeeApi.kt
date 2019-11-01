package com.denarii.uphold.models


import com.denarii.uphold.enums.ApiTarget
import com.denarii.uphold.enums.FeeType
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.serializer.SupportedCurrencySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeeApi(

    @SerialName("amount")
    val amount: String,

    @SerialName("currency")
    @Serializable(SupportedCurrencySerializer::class)
    val currency: SupportedCurrency,

    @SerialName("percentage")
    val percentage: Double,

    @SerialName("target")
    val target: ApiTarget,

    @SerialName("type")
    val type: FeeType
)