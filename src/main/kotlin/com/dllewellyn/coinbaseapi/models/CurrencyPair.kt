package com.dllewellyn.coinbaseapi.models

data class CurrencyPair(
    val baseCurrency: String,
    val baseMaxSize: String,
    val baseMinSize: String,
    val id: String,
    val quoteCurrency: String,
    val quoteIncrement: String
)