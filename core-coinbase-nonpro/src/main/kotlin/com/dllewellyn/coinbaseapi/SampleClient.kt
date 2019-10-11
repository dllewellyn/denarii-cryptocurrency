package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        with(CoinbaseApi()) {


            println(exchangeRateRetriever().getExchangeRates(CryptoCurrency.GBP))
            //println(currencyList().getCurrencyList())
            //println(prices().getSpotPrice(CurrencyPair.fromId("BTC-USD")))
        }
    }
}