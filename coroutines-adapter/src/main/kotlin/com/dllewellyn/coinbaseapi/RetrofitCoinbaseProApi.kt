package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.adapter.*
import com.dllewellyn.coinbaseapi.interfaces.*

object RetrofitCoinbaseProApi : CoinbaseProApi {

    var sandbox = false

    private val retrofit: RetrofitCoroutinesBuilder by lazy {
        RetrofitCoroutinesBuilder(sandbox)
    }

    override fun currencies(): CurrencyList = CurrencyListAdapter(retrofit)
    override fun exchangeRates(): ExchangeRateRetriver = ExchangeRateRetriverAdapter(retrofit)
    override fun currencyPairs(): CurrencyPairsList = CurrencyPairAdapter(retrofit)
    override fun buyAndSellPrices(): CurrencyPrice = CurrencyPriceAdapter(retrofit)
    override fun productTicker(): ProductTickerRetriever = ProductTickerRetrieverAdapter(retrofit)
    override fun twentyFourHours(): TwentyFourHourStatsRetriever = TwentyFourHourStatsRetrieverAdapter(retrofit)

}