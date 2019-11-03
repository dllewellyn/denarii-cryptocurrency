package com.dllewellyn.denarii.models.marketinfo

import com.ionspin.kotlin.bignum.decimal.BigDecimal

data class TwentyFourHourStats(
    val openAt: BigDecimal,
    val high: BigDecimal,
    val low: BigDecimal,
    val volume: BigDecimal
)