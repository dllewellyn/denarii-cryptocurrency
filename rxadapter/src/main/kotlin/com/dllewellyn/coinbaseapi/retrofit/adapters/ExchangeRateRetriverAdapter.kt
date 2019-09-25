package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
import com.dllewellyn.coinbaseapi.models.ExchangeRates
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.models.marketinfo.ProductTicker
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder
import com.dllewellyn.coinbaseapi.retrofit.RetrofitRxApiBuilder
import io.reactivex.Single
import java.math.BigInteger

class ExchangeRateRetriverAdapter(private val retrofit: RetrofitRxApiBuilder) : ExchangeRateRetriver {
    override fun getProductTicker(cryptoCurrency: CryptoCurrency) =
        throw NotImplementedError()


    override fun getExchangeRates(cryptoCurrency: CryptoCurrency) =
        retrofit.getApi()
            .getExchangeRates(cryptoCurrency.str)
            .map { ExchangeRates(cryptoCurrency, it.data.rates.mapValues { rate -> BigInteger(rate.value) }) }

}