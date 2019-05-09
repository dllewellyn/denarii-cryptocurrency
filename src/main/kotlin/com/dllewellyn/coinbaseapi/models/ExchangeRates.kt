package com.dllewellyn.coinbaseapi.models

data class ExchangeRates(val cryptoCurrency: CryptoCurrency, val map : Map<String, Double>) {
    fun filterForCurrency(currency : String) = map[currency]
}