package com.dllewellyn.coinbaseapi.api.models


import com.google.gson.annotations.SerializedName

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