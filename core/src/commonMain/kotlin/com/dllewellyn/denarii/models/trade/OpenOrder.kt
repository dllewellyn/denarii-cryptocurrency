package com.dllewellyn.denarii.models.currency

import com.dllewellyn.denarii.models.marketinfo.BuyOrSell
import com.dllewellyn.denarii.models.trade.LimitOrMarket
import com.dllewellyn.denarii.models.trade.OrderStatus
import com.ionspin.kotlin.bignum.decimal.BigDecimal

data class OpenOrder(
    val createdAt: String,
    val executedValue: BigDecimal,
    val fillFees: BigDecimal,
    val fillSize: BigDecimal,
    val id: String,
    val postOnly: Boolean,
    val price: BigDecimal,
    val currencyPair: CurrencyPair,
    val settled: Boolean,
    val side: BuyOrSell,
    val size: BigDecimal,
    val status: OrderStatus,
    val stp: String?,
    val timeInForce: String,
    val type: LimitOrMarket
)