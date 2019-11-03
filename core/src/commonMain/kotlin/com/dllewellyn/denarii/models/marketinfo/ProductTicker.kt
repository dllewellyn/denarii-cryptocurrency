package com.dllewellyn.denarii.models.marketinfo

import com.ionspin.kotlin.bignum.decimal.BigDecimal


data class ProductTicker(
    val ask: BigDecimal,
    val bid: BigDecimal,
    val price: BigDecimal,
    val size: String,
    val time: String,
    val tradeId: Int,
    val volume: String
)