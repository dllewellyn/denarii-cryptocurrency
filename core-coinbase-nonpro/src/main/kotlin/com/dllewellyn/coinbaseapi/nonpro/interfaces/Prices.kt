package com.dllewellyn.coinbaseapi.nonpro.interfaces

import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.marketinfo.BuySellPrice

interface Prices {
    suspend fun getBuyPrice(pair: CurrencyPair) : BuySellPrice
    suspend fun getSellPrice(pair: CurrencyPair) : BuySellPrice
    suspend fun getSpotPrice(pair: CurrencyPair) : BuySellPrice
}