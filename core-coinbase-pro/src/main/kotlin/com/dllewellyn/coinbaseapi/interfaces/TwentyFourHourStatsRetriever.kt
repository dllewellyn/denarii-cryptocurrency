package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.marketinfo.TwentyFourHourStats
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument

abstract class TwentyFourHourStatsRetriever : ReadOnlyRepositoryArgument<CurrencyPair, TwentyFourHourStats> {
    suspend fun get24HourStats(cryptoCurrency: CurrencyPair) = retrieveData(cryptoCurrency)
}