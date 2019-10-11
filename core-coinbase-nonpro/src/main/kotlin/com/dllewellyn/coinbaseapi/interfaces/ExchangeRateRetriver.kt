package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.BuyOrSell
import com.dllewellyn.coinbaseapi.models.ExchangeRates
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.models.marketinfo.BuySellPrice
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository

abstract class ExchangeRateRetriver : ReadOnlyPostRepository<CryptoCurrency, List<BuySellPrice>> {
    suspend fun getExchangeRates(cryptoCurrency: CryptoCurrency) = retrieveData(cryptoCurrency)
}

suspend fun ExchangeRates.filterByCurrency(currency: String) = filterForCurrency(currency)