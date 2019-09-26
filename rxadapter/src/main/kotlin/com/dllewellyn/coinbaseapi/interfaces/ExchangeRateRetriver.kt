package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.ExchangeRates
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.marketinfo.ProductTicker
import com.dllewellyn.coinbaseapi.models.marketinfo.TwentyFourHourStats
import io.reactivex.Single

interface ExchangeRateRetriver {
    fun getExchangeRates(cryptoCurrency : CryptoCurrency) : Single<ExchangeRates>
    fun getProductTicker(cryptoCurrency: CurrencyPair) : Single<ProductTicker>
    fun get24HourStats(cryptoCurrency: CurrencyPair) : Single<TwentyFourHourStats>

}

fun Single<ExchangeRates>.filterByCurrency(currency : String) = map { it.filterForCurrency(currency) }