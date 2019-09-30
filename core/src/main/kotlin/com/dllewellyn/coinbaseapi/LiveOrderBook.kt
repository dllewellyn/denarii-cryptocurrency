package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.models.EventResponse
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair

interface LiveOrderBook {
    fun getMonitoredCurrencies(): List<CurrencyPair>
    fun getBuyPriceForPair(pair: CurrencyPair): EventResponse.Level2Update?
    fun getSellPriceForPair(pair: CurrencyPair): EventResponse.Level2Update?
}