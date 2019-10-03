package com.dllewellyn.coinbaseapi.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable

data class ApiCurrencyRates(
    val data: ApiList
)

@Serializable

data class ApiList(
    val currency: String,
    val rates: Map<String, String>
)