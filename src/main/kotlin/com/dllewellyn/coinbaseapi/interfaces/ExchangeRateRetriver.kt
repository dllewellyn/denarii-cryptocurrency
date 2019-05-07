package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.Currency
import com.dllewellyn.coinbaseapi.models.ExchangeRates
import io.reactivex.Single

interface ExchangeRateRetriver {
    fun getExchangeRates(currency : Currency) : Single<ExchangeRates>
}

fun Single<ExchangeRates>.filterByCurrency(currency : String) = map { it.filterForCurrency(currency) }