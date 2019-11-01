package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import com.dllewellyn.denarii.models.ExchangeRates
import com.dllewellyn.denarii.models.currency.CryptoCurrency
import com.dllewellyn.denarii.models.marketinfo.BuySellPrice

abstract class ExchangeRateRetriver : ReadOnlyPostRepository<CryptoCurrency, List<BuySellPrice>> {
    suspend fun getExchangeRates(cryptoCurrency: CryptoCurrency) = retrieveData(cryptoCurrency)
}

suspend fun ExchangeRates.filterByCurrency(currency: String) = filterForCurrency(currency)