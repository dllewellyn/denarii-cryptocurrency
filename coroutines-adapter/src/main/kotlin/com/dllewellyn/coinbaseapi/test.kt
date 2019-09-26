package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {

        print(
            RetrofitApi.exchangeRates()
                .getProductTicker(CurrencyPair.fromId("BAT-ETH"))
        )

    }
}