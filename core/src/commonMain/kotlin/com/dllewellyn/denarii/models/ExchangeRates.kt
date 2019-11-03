package com.dllewellyn.denarii.models

import com.dllewellyn.denarii.models.currency.CryptoCurrency
import com.ionspin.kotlin.bignum.decimal.BigDecimal

data class ExchangeRates(val cryptoCurrency: CryptoCurrency, val map : Map<String, BigDecimal>) {
    fun filterForCurrency(currency : String) = map[currency]
}