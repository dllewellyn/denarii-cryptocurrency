package com.dllewellyn.coinbaseapi.models.currency

data class CurrencyPair(
    val baseCurrency: String,
    val baseMaxSize: String ? = "",
    val baseMinSize: String ? = "",
    val id: String,
    val quoteCurrency: String,
    val quoteIncrement: String ?= ""
) {
    fun containsCurrency(vararg supportedCurrency: SupportedCurrency) = supportedCurrency.any {
        baseCurrency == it.id || quoteCurrency == it.id
    }
}