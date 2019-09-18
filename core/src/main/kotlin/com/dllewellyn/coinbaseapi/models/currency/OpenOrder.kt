package com.dllewellyn.coinbaseapi.models.currency

import com.dllewellyn.coinbaseapi.models.BuyOrSell
import com.dllewellyn.coinbaseapi.models.trade.LimitOrMarket
import com.dllewellyn.coinbaseapi.models.trade.OrderStatus

data class OpenOrder(
    val createdAt: String,
    val executedValue: Double,
    val fillFees: Double,
    val fillSize: Double,
    val id: String,
    val postOnly: Boolean,
    val price: Double,
    val currencyPair: CurrencyPair,
    val settled: Boolean,
    val side: BuyOrSell,
    val size: Double,
    val status: OrderStatus,
    val stp: String?,
    val timeInForce: String,
    val type: LimitOrMarket)