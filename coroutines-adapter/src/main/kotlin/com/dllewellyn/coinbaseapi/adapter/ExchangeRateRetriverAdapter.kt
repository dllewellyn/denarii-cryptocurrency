package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.RetrofitCoroutinesBuilder
import com.dllewellyn.coinbaseapi.extensions.unwrap
import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
import com.dllewellyn.coinbaseapi.models.ExchangeRates
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency


class ExchangeRateRetriverAdapter(private val retrofitCoroutinesBuilder: RetrofitCoroutinesBuilder) :
    ExchangeRateRetriver {

    override suspend fun getExchangeRates(cryptoCurrency: CryptoCurrency) =
        retrofitCoroutinesBuilder.getApi().getExchangeRates(cryptoCurrency.str).unwrap().let {
            ExchangeRates(
                cryptoCurrency,
                it.data.rates.mapValues { rate -> rate.value.toDouble() })
        }

}