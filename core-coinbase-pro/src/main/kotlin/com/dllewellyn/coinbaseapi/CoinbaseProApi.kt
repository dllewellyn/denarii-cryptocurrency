package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.interfaces.*

interface CoinbaseProApi {
    fun productTicker() : ProductTickerRetriever
    fun twentyFourHours() : TwentyFourHourStatsRetriever
    fun currencyPairs(): CurrencyPairsList
    fun buyAndSellPrices(): CurrencyPrice

}