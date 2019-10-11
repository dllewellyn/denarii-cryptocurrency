package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import com.dllewellyn.coinbaseapi.models.marketinfo.BuySellPrice
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ApiValueResponse(
    val data: ApiBuyOrSell
)

@Serializable
data class ApiBuyOrSell(
    val base : String,
    val amount: String,
    val currency: String
)

fun ApiValueResponse.toCore(buyOrSell: BuyOrSell) = BuySellPrice(
    buyOrSell, BigDecimal(data.amount), SupportedCurrency(data.currency), "coinbase"
)