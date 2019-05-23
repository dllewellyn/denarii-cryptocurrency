package com.dllewellyn.coinbaseapi.retrofit.models

data class ApiTradeTick(
    val ask: String?,
    val bid: String?,
    val price: String,
    val size: String,
    val time: String,
    val trade_id: Int,
    val volume: String
)