package com.dllewellyn.coinbaseapi.retrofit.models

import com.dllewellyn.coinbaseapi.models.trade.OrderResponse
import com.google.gson.annotations.SerializedName

data class ApiOrderResponse(
    @SerializedName("created_at") val created_at: String,
    @SerializedName("executed_value")  val executed_value: String,
    @SerializedName("fill_fees") val fill_fees: String,
    @SerializedName("filled_size") val filled_size: String,
    @SerializedName("id") val id: String,
    @SerializedName("post_only") val post_only: Boolean,
    @SerializedName("price") val price: String,
    @SerializedName("product_id") val product_id: String,
    @SerializedName("settled") val settled: Boolean,
    @SerializedName("side") val side: String,
    @SerializedName("size") val size: String,
    @SerializedName("status") val status: String,
    @SerializedName("stp") val stp: String,
    @SerializedName("time_in_force") val time_in_force: String,
    @SerializedName("type") val type: String
) {
    fun toCore() =
        OrderResponse(
            created_at,
            executed_value,
            fill_fees,
            filled_size,
            id,
            post_only,
            price,
            product_id,
            settled,
            size,
            size,
            status,
            stp,
            time_in_force,
            type
        )
}