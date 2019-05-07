package com.dllewellyn.coinbaseapi.models

data class ExchangeRates(val currency: Currency, val map : Map<String, Double>) {
    fun filterForCurrency(currency : String) = map[currency]
}