package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.models.currency.CurrencyValue
import com.dllewellyn.coinbaseapi.models.trade.CurrencyBuyAndSell


enum class BuyOrSell(val v : String) {
    BUY("buy"),
    SELL("sell")
}

sealed class EventResponse {
    data class Level2Snapshot(val buyAndSell: CurrencyBuyAndSell) : EventResponse()
    data class Level2Update(val buyAndSell: CurrencyValue, val buyOrSell: BuyOrSell) : EventResponse()
}

fun <T>Any.only(): List<T> = listOf(this as T)