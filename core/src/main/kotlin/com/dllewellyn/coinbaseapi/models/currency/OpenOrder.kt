package com.dllewellyn.coinbaseapi.models.currency

import com.dllewellyn.coinbaseapi.models.BuyOrSell
import com.dllewellyn.coinbaseapi.models.trade.LimitOrMarket
import com.dllewellyn.coinbaseapi.models.trade.OrderStatus
import java.math.BigInteger

data class OpenOrder(
    val createdAt: String,
    val executedValue: BigInteger,
    val fillFees: BigInteger,
    val fillSize: BigInteger,
    val id: String,
    val postOnly: Boolean,
    val price: BigInteger,
    val currencyPair: CurrencyPair,
    val settled: Boolean,
    val side: BuyOrSell,
    val size: BigInteger,
    val status: OrderStatus,
    val stp: String?,
    val timeInForce: String,
    val type: LimitOrMarket)