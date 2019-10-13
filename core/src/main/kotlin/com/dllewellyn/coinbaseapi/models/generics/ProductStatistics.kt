package com.dllewellyn.coinbaseapi.models.generics

import java.math.BigDecimal

enum class Direction {
    UP,
    DOWN
}

data class ProductStatistics(
    val openAt: BigDecimal,
    val high: BigDecimal,
    val low: BigDecimal,
    val volume: BigDecimal,
    val percentageChange: Double,
    val direction: Direction = if (percentageChange > 0) {
        Direction.UP
    } else {
        Direction.DOWN
    }
)