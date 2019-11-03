package com.dllewellyn.denarii.models.marketinfo

import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.ionspin.kotlin.bignum.decimal.BigDecimal

data class BuySellPrice(
    val buySellSpot: BuyOrSell,
    val amount: BigDecimal,
    val currency: SupportedCurrency,
    val platform: String
)