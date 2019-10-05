package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.marketinfo.BuySellPrice

interface Prices {
    suspend fun getBuyPrice(pair: CurrencyPair) : BuySellPrice
    suspend fun getSellPrice(pair: CurrencyPair) : BuySellPrice
    suspend fun getSpotPrice(pair: CurrencyPair) : BuySellPrice
}