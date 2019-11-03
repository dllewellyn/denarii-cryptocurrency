package com.dllewellyn.denarii.models.marketinfo

import com.ionspin.kotlin.bignum.decimal.BigDecimal


enum class Direction {
    UP,
    DOWN
}

data class ProductStatistics(
    val openAt: BigDecimal,
    val high: BigDecimal,
    val low: BigDecimal,
    val volume: BigDecimal,
    val percentageChange: BigDecimal,
    val direction: Direction = if (percentageChange > 0) {
        Direction.UP
    } else {
        Direction.DOWN
    }
)