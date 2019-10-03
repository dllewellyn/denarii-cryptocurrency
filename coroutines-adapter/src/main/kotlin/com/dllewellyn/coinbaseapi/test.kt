package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {

        val batToEth = CurrencyPair.fromId("BAT-ETH")
        println(RetrofitCoinbaseProApi.productTicker().getProductTicker(batToEth))
        println(RetrofitCoinbaseProApi.twentyFourHours().get24HourStats(batToEth))
        println(RetrofitCoinbaseProApi.buyAndSellPrices().getProductOrderBook(batToEth))
    }

}