package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCurrencies(
    val data: List<ApiCurrency>
)

@Serializable
data class ApiCurrency(
    val id: String,
    val name: String,
    val minSize: String
)

fun ApiCurrency.toCurrency() =
    SupportedCurrency(id, name, minSize.toDouble())