package com.dllewellyn.coinbaseapi.models.currency

import com.dllewellyn.coinbaseapi.models.trade.LimitOrMarket

data class OpenOrder(
    val createdAt: String,
    val executedValue: String,
    val fillFees: String,
    val fillSize: String,
    val id: String,
    val postOnly: Boolean,
    val price: String,
    val currencyPair: CurrencyPair,
    val settled: Boolean,
    val side: String,
    val size: String,
    val status: String,
    val stp: String?,
    val timeInForce: String,
    val type: LimitOrMarket)