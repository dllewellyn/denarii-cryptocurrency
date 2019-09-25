package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import java.math.BigInteger

data class ExchangeRates(val cryptoCurrency: CryptoCurrency, val map : Map<String, BigInteger>) {
    fun filterForCurrency(currency : String) = map[currency]
}