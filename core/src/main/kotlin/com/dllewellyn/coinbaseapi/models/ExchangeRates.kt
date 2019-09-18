package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency

data class ExchangeRates(val cryptoCurrency: CryptoCurrency, val map : Map<String, Double>) {
    fun filterForCurrency(currency : String) = map[currency]
}