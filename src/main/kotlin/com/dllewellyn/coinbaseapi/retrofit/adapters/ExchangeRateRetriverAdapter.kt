package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
import com.dllewellyn.coinbaseapi.models.Currency
import com.dllewellyn.coinbaseapi.models.ExchangeRates
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder

class ExchangeRateRetriverAdapter(private val retrofit: RetrofitApiBuilder) : ExchangeRateRetriver {

    override fun getExchangeRates(currency: Currency) =
        retrofit.getApi()
            .getExchangeRates(currency.str)
            .map { ExchangeRates(currency, it.data.rates.mapValues { rate -> rate.value.toDouble() }) }

}