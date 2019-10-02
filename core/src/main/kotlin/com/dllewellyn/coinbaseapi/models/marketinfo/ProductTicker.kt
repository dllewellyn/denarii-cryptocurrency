package com.dllewellyn.coinbaseapi.models.marketinfo

import java.math.BigDecimal

data class ProductTicker(
    val ask: BigDecimal,
    val bid: BigDecimal,
    val price: BigDecimal,
    val size: String,
    val time: String,
    val tradeId: Int,
    val volume: String
)