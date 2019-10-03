package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import java.math.BigDecimal

data class ExchangeRates(val cryptoCurrency: CryptoCurrency, val map : Map<String, BigDecimal>) {
    fun filterForCurrency(currency : String) = map[currency]
}