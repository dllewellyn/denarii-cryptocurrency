package com.dllewellyn.coinbaseapi.models.currency

data class SupportedCurrency(
    val id: String,
    val name: String = "",
    val minSize: Double = 0.0)
