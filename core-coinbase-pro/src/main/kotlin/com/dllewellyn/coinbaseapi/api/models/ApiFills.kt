package com.dllewellyn.coinbaseapi.api.models

import com.dllewellyn.coinbaseapi.models.accounts.Fills
import com.dllewellyn.coinbaseapi.models.accounts.LIQUIDITY
import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.marketinfo.BuyOrSell
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

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

fun ApiFills.toCore() = Fills(
    tradeId,
    CurrencyPair.fromId(productId),
    BigDecimal(price),
    BigDecimal(size),
    orderId,
    createdAt,
    LIQUIDITY.fromString(liquidity),
    BigDecimal(fee),
    settled,
    BuyOrSell.fromString(side)
)