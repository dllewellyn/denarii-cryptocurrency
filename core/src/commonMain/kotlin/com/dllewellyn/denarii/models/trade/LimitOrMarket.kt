package com.dllewellyn.denarii.models.trade

enum class LimitOrMarket(val v : String) {
    LIMIT("limit"),
    MARKET("market");

    companion object {
        fun fromString(s : String) = when(s) {
            "limit" -> LIMIT
            "market" -> MARKET
            else ->  throw IllegalArgumentException()
        }
    }
}
