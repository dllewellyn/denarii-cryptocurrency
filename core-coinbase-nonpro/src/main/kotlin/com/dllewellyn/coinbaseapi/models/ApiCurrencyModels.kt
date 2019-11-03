package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.denarii.models.currency.SupportedCurrency
import kotlinx.serialization.Serializable

@Serializable
data class ApiCurrencies(
    val data: List<ApiCurrency>
)

@Serializable
data class ApiCurrency(
    val id: String,
    val name: String,
    val min_size: String
)

fun ApiCurrency.toCurrency() =
    SupportedCurrency(id, name, min_size.toDouble())