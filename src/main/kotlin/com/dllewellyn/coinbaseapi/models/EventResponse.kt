package com.dllewellyn.coinbaseapi.models


enum class BuyOrSell {
    BUY,
    SELL
}

sealed class EventResponse {
    data class Level2Snapshot(val buyAndSell: CurrencyBuyAndSell) : EventResponse()
    data class Level2Update(val buyAndSell: CurrencyValue, val buyOrSell: BuyOrSell) : EventResponse()
}

fun <T>Any.only(): List<T> = listOf(this as T)