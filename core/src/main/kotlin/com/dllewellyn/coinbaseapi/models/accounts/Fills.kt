package com.dllewellyn.coinbaseapi.models.accounts

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import java.lang.IllegalArgumentException
import java.math.BigDecimal

enum class LIQUIDITY(val v : String) {
    TAKER("T"),
    MAKER("M");

    companion object {
        fun fromString(v : String) = when(v) {
            "T" -> TAKER
            "M" -> MAKER
            else -> throw IllegalArgumentException()
        }
    }
}
data class Fills(
    val tradeId: Int,
    val productId: CurrencyPair,
    val price: BigDecimal,
    val size: BigDecimal,
    val orderId: String,
    val createdAt: String,
    val liquidity: String, //The liquidity field indicates if the fill was the result of a liquidity provider or liquidity taker. M indicates Maker and T indicates Taker.
    val fee: String,
    val settled: Boolean,
    val side: String
)