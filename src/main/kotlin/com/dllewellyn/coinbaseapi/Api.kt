package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.interfaces.CurrencyList
import com.dllewellyn.coinbaseapi.interfaces.CurrencyPairsList
import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder
import com.dllewellyn.coinbaseapi.retrofit.adapters.CurrencyListAdapter
import com.dllewellyn.coinbaseapi.retrofit.adapters.CurrencyPairAdapter
import com.dllewellyn.coinbaseapi.retrofit.adapters.ExchangeRateRetriverAdapter

object Api {

    private val retrofit : RetrofitApiBuilder by lazy {
        RetrofitApiBuilder()
    }

    fun currencies() : CurrencyList = CurrencyListAdapter(retrofit)
    fun exchangeRates() : ExchangeRateRetriver = ExchangeRateRetriverAdapter(retrofit)
    fun currencyPairs() : CurrencyPairsList = CurrencyPairAdapter(retrofit)
}