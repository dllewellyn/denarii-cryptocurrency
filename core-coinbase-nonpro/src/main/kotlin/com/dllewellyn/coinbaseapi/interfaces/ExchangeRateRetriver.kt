package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.denarii.models.ExchangeRates
import com.dllewellyn.denarii.models.currency.CryptoCurrency
import com.dllewellyn.denarii.models.marketinfo.BuySellPrice
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument

abstract class ExchangeRateRetriver : ReadOnlyRepositoryArgument<CryptoCurrency, List<BuySellPrice>> {
    suspend fun getExchangeRates(cryptoCurrency: CryptoCurrency) = retrieveData(cryptoCurrency)
}

suspend fun ExchangeRates.filterByCurrency(currency: String) = filterForCurrency(currency)