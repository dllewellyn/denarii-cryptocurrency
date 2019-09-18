package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
import com.dllewellyn.coinbaseapi.models.ExchangeRates
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder
import com.dllewellyn.coinbaseapi.retrofit.RetrofitRxApiBuilder

class ExchangeRateRetriverAdapter(private val retrofit: RetrofitRxApiBuilder) : ExchangeRateRetriver {

    override fun getExchangeRates(cryptoCurrency: CryptoCurrency) =
        retrofit.getApi()
            .getExchangeRates(cryptoCurrency.str)
            .map { ExchangeRates(cryptoCurrency, it.data.rates.mapValues { rate -> rate.value.toDouble() }) }

}