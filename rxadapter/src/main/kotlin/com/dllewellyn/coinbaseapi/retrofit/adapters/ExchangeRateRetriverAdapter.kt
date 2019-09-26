package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.api.models.toCore
import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
import com.dllewellyn.coinbaseapi.models.ExchangeRates
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.retrofit.RetrofitRxApiBuilder
import java.math.BigDecimal

class ExchangeRateRetriverAdapter(private val retrofit: RetrofitRxApiBuilder) : ExchangeRateRetriver {
    override fun get24HourStats(cryptoCurrency: CurrencyPair) =
        retrofit.getProApi().get24HourStats(cryptoCurrency.id).map {
            it.toCore()
        }

    override fun getProductTicker(cryptoCurrency: CurrencyPair) =
        retrofit.getProApi()
            .getProductTicker(cryptoCurrency.id)
            .map { it.toCore() }


    override fun getExchangeRates(cryptoCurrency: CryptoCurrency) =
        retrofit.getApi()
            .getExchangeRates(cryptoCurrency.str)
            .map { ExchangeRates(cryptoCurrency, it.data.rates.mapValues { rate -> BigDecimal(rate.value) }) }

}