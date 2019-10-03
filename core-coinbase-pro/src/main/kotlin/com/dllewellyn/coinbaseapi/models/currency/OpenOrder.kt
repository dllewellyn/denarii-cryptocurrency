package com.dllewellyn.coinbaseapi.models.currency

import com.dllewellyn.coinbaseapi.models.BuyOrSell
import com.dllewellyn.coinbaseapi.models.trade.LimitOrMarket
import com.dllewellyn.coinbaseapi.models.trade.OrderStatus
import java.math.BigDecimal

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
    val type: LimitOrMarket)