package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.ExchangeRates
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.models.marketinfo.ProductTicker
import io.reactivex.Single

interface ExchangeRateRetriver {
    fun getExchangeRates(cryptoCurrency : CryptoCurrency) : Single<ExchangeRates>
    fun getProductTicker(cryptoCurrency: CryptoCurrency) : Single<ProductTicker>
}

fun Single<ExchangeRates>.filterByCurrency(currency : String) = map { it.filterForCurrency(currency) }