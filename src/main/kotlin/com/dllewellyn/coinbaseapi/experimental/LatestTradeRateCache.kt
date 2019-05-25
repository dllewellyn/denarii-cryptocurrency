package com.dllewellyn.coinbaseapi.experimental

import com.dllewellyn.coinbaseapi.Api
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import retrofit2.adapter.rxjava2.HttpException
import java.util.concurrent.TimeUnit

class ValueCache {
    var buy: Double = 0.0
    var sell: Double = 0.0
}

class LatestTradeRateCache {

    val valuesCache = ValueCache()

    fun latestTradeRateCache(currencyPair: CurrencyPair) =
        Api.buyAndSellPrices()
            .getCurrencyBuyAndSellPrice(currencyPair)
            .doAfterSuccess {
                valuesCache.buy = it.buy
                valuesCache.sell = it.sell
            }
            .retryWhen {
                it.map {
                    x ->
                    if (x is HttpException) {
                        if (x.code() == 429) {
                            it.delay(1, TimeUnit.SECONDS)
                        }
                    }
                }
            }
            .repeatWhen { it.delay(1, TimeUnit.SECONDS) }
}