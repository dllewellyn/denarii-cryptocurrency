package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.adapter.CurrencyListAdapter
import com.dllewellyn.coinbaseapi.adapter.CurrencyPairAdapter
import com.dllewellyn.coinbaseapi.adapter.CurrencyPriceAdapter
import com.dllewellyn.coinbaseapi.adapter.ExchangeRateRetriverAdapter
import com.dllewellyn.coinbaseapi.interfaces.CurrencyList
import com.dllewellyn.coinbaseapi.interfaces.CurrencyPairsList
import com.dllewellyn.coinbaseapi.interfaces.CurrencyPrice
import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver

object RetrofitApi {
    var sandbox = false

    private val retrofit : RetrofitCoroutinesBuilder by lazy {
        RetrofitCoroutinesBuilder(sandbox)
    }


    fun currencies() : CurrencyList = CurrencyListAdapter(retrofit)
    fun exchangeRates() : ExchangeRateRetriver = ExchangeRateRetriverAdapter(retrofit)
    fun currencyPairs() : CurrencyPairsList = CurrencyPairAdapter(retrofit)
    fun buyAndSellPrices() : CurrencyPrice = CurrencyPriceAdapter(retrofit)
}