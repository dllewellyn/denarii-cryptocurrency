package com.dllewellyn.coinbaseapi.utils

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import com.dllewellyn.coinbaseapi.models.generics.ProductStatistics
import com.dllewellyn.coinbaseapi.models.marketinfo.ProductTicker
import com.dllewellyn.coinbaseapi.models.marketinfo.TwentyFourHourStats
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository

class StatisticsCalculator(
    private val ticker: ReadOnlyPostRepository<CurrencyPair, ProductTicker>,
    private val statistics: ReadOnlyPostRepository<CurrencyPair, TwentyFourHourStats>
) {

    suspend fun changeCalculator(pair: CurrencyPair): Double {
        val tickerResult = ticker.retrieveData(pair)
        val statistics = statistics.retrieveData(pair)
        return calculate(tickerResult, statistics)
    }

    suspend fun changeCalculator(supportedCurrency: SupportedCurrency) =
        changeCalculator(CurrencyPair.fromId("${supportedCurrency.id}-USDC"))

    suspend fun createStatisticsForCurrency(supportedCurrency: SupportedCurrency): ProductStatistics {
        val pair = CurrencyPair.fromId("${supportedCurrency.id}-USDC")

        val tickerResult = ticker.retrieveData(pair)
        val statistics = statistics.retrieveData(pair)

        return ProductStatistics(
            statistics.openAt,
            statistics.high,
            statistics.low,
            statistics.volume,
            calculate(tickerResult, statistics)
        )
    }

    private fun calculate(tickerResult: ProductTicker, statistics: TwentyFourHourStats) =
        (tickerResult.price.toDouble() - statistics.openAt.toDouble()) / statistics.openAt.toDouble() * 100
}