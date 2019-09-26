package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.ExchangeRates
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.marketinfo.ProductTicker

interface ExchangeRateRetriver {
    suspend fun getExchangeRates(cryptoCurrency: CryptoCurrency): ExchangeRates
    suspend fun getProductTicker(cryptoCurrency: CurrencyPair): ProductTicker
}

suspend fun ExchangeRates.filterByCurrency(currency: String) = filterForCurrency(currency)