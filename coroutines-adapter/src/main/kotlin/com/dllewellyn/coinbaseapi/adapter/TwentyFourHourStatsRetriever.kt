package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.RetrofitCoroutinesBuilder
import com.dllewellyn.coinbaseapi.api.models.toCore
import com.dllewellyn.coinbaseapi.extensions.unwrap
import com.dllewellyn.coinbaseapi.interfaces.TwentyFourHourStatsRetriever
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair

class TwentyFourHourStatsRetrieverAdapter(private val retrofitCoroutinesBuilder: RetrofitCoroutinesBuilder) :
    TwentyFourHourStatsRetriever() {

    override suspend fun retrieveData(arg: CurrencyPair) =
        retrofitCoroutinesBuilder.getProApi().get24HourStats(arg.id).unwrap().toCore()

}