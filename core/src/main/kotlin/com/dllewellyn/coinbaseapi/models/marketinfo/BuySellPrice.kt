package com.dllewellyn.coinbaseapi.models.marketinfo

import com.dllewellyn.coinbaseapi.models.BuyOrSell
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import java.math.BigDecimal

data class BuySellPrice(
    val buySellSpot: BuyOrSell,
    val amount: BigDecimal,
    val currency: SupportedCurrency
)