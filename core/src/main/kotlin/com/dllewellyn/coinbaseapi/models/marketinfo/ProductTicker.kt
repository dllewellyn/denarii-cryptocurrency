package com.dllewellyn.coinbaseapi.models.marketinfo

import java.math.BigInteger

data class ProductTicker(
    val ask: BigInteger,
    val bid: BigInteger,
    val price: String,
    val size: String,
    val time: String,
    val tradeId: Int,
    val volume: String
)