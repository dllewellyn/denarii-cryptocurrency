package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.marketinfo.TwentyFourHourStats
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository

abstract class TwentyFourHourStatsRetriever : ReadOnlyPostRepository<CurrencyPair, TwentyFourHourStats> {
    suspend fun get24HourStats(cryptoCurrency: CurrencyPair) = retrieveData(cryptoCurrency)
}