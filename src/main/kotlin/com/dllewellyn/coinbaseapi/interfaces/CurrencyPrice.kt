package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.CurrencyValue
import com.dllewellyn.coinbaseapi.models.trade.CurrencyBuyAndSell
import io.reactivex.Single

interface CurrencyPrice {
    fun getCurrencyBuyPrice(pair : CurrencyPair) : Single<CurrencyValue>
    fun getCurrencySellPrice(pair : CurrencyPair) : Single<CurrencyValue>
    fun getCurrencyBuyAndSellPrice(pair: CurrencyPair) : Single<CurrencyBuyAndSell>

}