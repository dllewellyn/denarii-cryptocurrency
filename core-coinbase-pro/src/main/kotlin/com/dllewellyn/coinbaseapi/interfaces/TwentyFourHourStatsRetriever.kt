package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.marketinfo.TwentyFourHourStats

abstract class TwentyFourHourStatsRetriever : ReadOnlyPostRepository<CurrencyPair, TwentyFourHourStats> {
    suspend fun get24HourStats(cryptoCurrency: CurrencyPair) = retrieveData(cryptoCurrency)
}