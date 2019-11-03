package com.dllewellyn.denarii.models.marketinfo

enum class BuyOrSell(val v: String) {
    BUY("buy"),
    SPOT("spot"),
    SELL("sell");

    companion object {
        fun fromString(v: String) = when (v) {
            "buy" -> BUY
            "sell" -> SELL
            "spot" -> SPOT
            else -> throw IllegalArgumentException()
        }
    }
}