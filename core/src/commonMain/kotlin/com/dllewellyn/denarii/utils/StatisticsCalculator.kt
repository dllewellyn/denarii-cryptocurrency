package com.dllewellyn.denarii.utils

import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.models.marketinfo.ProductStatistics
import com.dllewellyn.denarii.models.marketinfo.ProductTicker
import com.dllewellyn.denarii.models.marketinfo.TwentyFourHourStats
import com.ionspin.kotlin.bignum.decimal.BigDecimal

class StatisticsCalculator(
    private val ticker: ReadOnlyPostRepository<CurrencyPair, ProductTicker>,
    private val statistics: ReadOnlyPostRepository<CurrencyPair, TwentyFourHourStats>
) {

    suspend fun changeCalculator(pair: CurrencyPair): BigDecimal {
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
        (tickerResult.price.minus(statistics.openAt).divide(statistics.openAt).multiply(BigDecimal.fromInt(100)))
}