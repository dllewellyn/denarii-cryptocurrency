package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.ExchangeRates
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency

interface ExchangeRateRetriver {
    suspend fun getExchangeRates(cryptoCurrency : CryptoCurrency) : ExchangeRates
}

suspend fun ExchangeRates.filterByCurrency(currency : String) = filterForCurrency(currency)