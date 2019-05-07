package com.dllewellyn.coinbaseapi.models

data class SupportedCurrency(
    val id: String,
    val name: String,
    val minSize: Double
)