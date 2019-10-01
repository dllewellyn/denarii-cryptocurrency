package com.dllewellyn.coinbaseapi.api.models

import com.google.gson.annotations.SerializedName

data class ApiFills(
    @SerializedName("trade_id") val tradeId: Int,
    @SerializedName("product_id") val productId: String,
    @SerializedName("price") val price: String,
    @SerializedName("size") val size: String,
    @SerializedName("order_id") val orderId: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("liquidity") val liquidity: String, //The liquidity field indicates if the fill was the result of a liquidity provider or liquidity taker. M indicates Maker and T indicates Taker.
    @SerializedName("fee") val fee: String,
    @SerializedName("settled") val settled: Boolean,
    @SerializedName("side") val side: String
)

