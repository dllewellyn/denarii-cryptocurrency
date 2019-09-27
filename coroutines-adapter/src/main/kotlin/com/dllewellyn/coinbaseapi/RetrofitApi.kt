package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.adapter.CurrencyListAdapter
import com.dllewellyn.coinbaseapi.adapter.CurrencyPairAdapter
import com.dllewellyn.coinbaseapi.adapter.CurrencyPriceAdapter
import com.dllewellyn.coinbaseapi.adapter.ExchangeRateRetriverAdapter
import com.dllewellyn.coinbaseapi.interfaces.CurrencyList
import com.dllewellyn.coinbaseapi.interfaces.CurrencyPairsList
import com.dllewellyn.coinbaseapi.interfaces.CurrencyPrice
import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver

object RetrofitApi : Api {
    var sandbox = false

    private val retrofit : RetrofitCoroutinesBuilder by lazy {
        RetrofitCoroutinesBuilder(sandbox)
    }

    override fun currencies() : CurrencyList = CurrencyListAdapter(retrofit)
    override fun exchangeRates() : ExchangeRateRetriver = ExchangeRateRetriverAdapter(retrofit)
    override fun currencyPairs() : CurrencyPairsList = CurrencyPairAdapter(retrofit)
    override fun buyAndSellPrices() : CurrencyPrice = CurrencyPriceAdapter(retrofit)
}