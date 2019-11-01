package com.dllewellyn.coinbaseapi.api.models


import com.dllewellyn.denarii.models.marketinfo.ProductTicker
import com.google.gson.annotations.SerializedName
import com.ionspin.kotlin.bignum.decimal.BigDecimal

data class ApiProductTicker(
    @SerializedName("ask")
    val ask: String,
    @SerializedName("bid")
    val bid: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("trade_id")
    val tradeId: Int,
    @SerializedName("volume")
    val volume: String
)

fun ApiProductTicker.toCore() = ProductTicker(
    BigDecimal.parseString(ask, 10),
    BigDecimal.parseString(bid, 10),
    BigDecimal.parseString(price, 10),
    size,
    time,
    tradeId,
    volume
)