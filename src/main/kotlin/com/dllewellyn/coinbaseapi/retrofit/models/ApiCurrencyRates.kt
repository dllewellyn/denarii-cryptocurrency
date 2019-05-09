package com.dllewellyn.coinbaseapi.retrofit.models

import com.google.gson.annotations.SerializedName

data class ApiCurrencyRates(
    @SerializedName("data") val data: ApiList
)

data class ApiList(
    @SerializedName("cryptoCurrency") val currency: String,
    @SerializedName("rates") val rates: Map<String, String>
)