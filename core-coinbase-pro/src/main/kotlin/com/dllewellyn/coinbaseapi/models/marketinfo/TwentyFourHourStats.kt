package com.dllewellyn.coinbaseapi.models.marketinfo

import java.math.BigDecimal

data class TwentyFourHourStats(
    val openAt: BigDecimal,
    val high: BigDecimal,
    val low: BigDecimal,
    val volume: BigDecimal
)