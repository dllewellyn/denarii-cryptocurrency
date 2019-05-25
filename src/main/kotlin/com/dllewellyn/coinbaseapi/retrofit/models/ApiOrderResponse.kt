package com.dllewellyn.coinbaseapi.retrofit.models

import com.dllewellyn.coinbaseapi.models.BuyOrSell
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.OpenOrder
import com.dllewellyn.coinbaseapi.models.trade.LimitOrMarket
import com.dllewellyn.coinbaseapi.models.trade.OrderStatus
import com.google.gson.annotations.SerializedName
import java.lang.IllegalArgumentException

data class ApiOrderResponse(
    @SerializedName("created_at") val created_at: String,
    @SerializedName("executed_value") val executed_value: String,
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
    @SerializedName("stp") val stp: String?,
    @SerializedName("time_in_force") val time_in_force: String,
    @SerializedName("type") val type: String
) {
    fun toCore() =
        OpenOrder(
            created_at,
            executed_value.toDouble(),
            fill_fees.toDouble(),
            filled_size.toDouble(),
            id,
            post_only,
            price.toDouble(),
            CurrencyPair.fromId(product_id),
            settled,
            when (side) {
                "buy" -> BuyOrSell.BUY
                "sell" -> BuyOrSell.SELL
                else -> throw IllegalArgumentException("Unexpected string buy and sell")
            },
            size.toDouble(),
            OrderStatus.fromString(status),
            stp,
            time_in_force,
            LimitOrMarket.fromString(type)
        )
}