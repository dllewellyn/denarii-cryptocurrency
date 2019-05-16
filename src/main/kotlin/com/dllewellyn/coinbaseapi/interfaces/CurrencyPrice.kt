package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.CurrencyBuyAndSell
import com.dllewellyn.coinbaseapi.models.CurrencyPair
import com.dllewellyn.coinbaseapi.models.CurrencyValue
import io.reactivex.Single

interface CurrencyPrice {
    fun getCurrencyBuyPrice(pair : CurrencyPair) : Single<CurrencyValue>
    fun getCurrencySellPrice(pair : CurrencyPair) : Single<CurrencyValue>
    fun getCurrencyBuyAndSellPrice(pair: CurrencyPair) : Single<CurrencyBuyAndSell>

}