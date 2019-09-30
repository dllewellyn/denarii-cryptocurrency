package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {

        val batToEth = CurrencyPair.fromId("BAT-ETH")

        println(RetrofitApi.productTicker().getProductTicker(batToEth))
        println(RetrofitApi.twentyFourHours().get24HourStats(batToEth))
        println(RetrofitApi.buyAndSellPrices().getProductOrderBook(batToEth))
    }

}