package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.ExchangeRates
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import io.reactivex.Single

interface ExchangeRateRetriver {
    fun getExchangeRates(cryptoCurrency : CryptoCurrency) : Single<ExchangeRates>
}

fun Single<ExchangeRates>.filterByCurrency(currency : String) = map { it.filterForCurrency(currency) }