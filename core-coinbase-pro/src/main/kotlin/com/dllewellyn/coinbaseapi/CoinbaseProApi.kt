package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.interfaces.*

interface CoinbaseProApi {
    fun currencies() : CurrencyList
    fun exchangeRates(): ExchangeRateRetriver
    fun productTicker() : ProductTickerRetriever
    fun twentyFourHours() : TwentyFourHourStatsRetriever
    fun currencyPairs(): CurrencyPairsList
    fun buyAndSellPrices(): CurrencyPrice

}