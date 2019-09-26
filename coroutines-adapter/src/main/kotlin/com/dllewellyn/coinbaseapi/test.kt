package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {

        val batToEth = CurrencyPair.fromId("BAT-ETH")

        println(RetrofitApi.exchangeRates().getProductTicker(batToEth))
        println(RetrofitApi.exchangeRates().get24HourStats(batToEth))

    }
}