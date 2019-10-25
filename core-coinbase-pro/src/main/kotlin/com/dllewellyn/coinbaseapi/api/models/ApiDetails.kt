package com.dllewellyn.coinbaseapi.api.models


import com.google.gson.annotations.SerializedName

data class ApiDetails(
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("trade_id")
    val tradeId: String
)