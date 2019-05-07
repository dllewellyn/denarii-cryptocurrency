package com.dllewellyn.coinbaseapi.retrofit.models

import com.dllewellyn.coinbaseapi.models.Currency
import com.google.gson.annotations.SerializedName


data class ApiCurrencies(
    @SerializedName("data") val data: List<ApiCurrency>
)

data class ApiCurrency(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("minSize") val minSize: String
)

fun ApiCurrency.toCurrency() =
        Currency(id, name, minSize.toDouble())