package com.dllewellyn.coinbaseapi.models.marketinfo

data class ProductTicker(
    val ask: String,
    val bid: String,
    val price: String,
    val size: String,
    val time: String,
    val tradeId: Int,
    val volume: String
)