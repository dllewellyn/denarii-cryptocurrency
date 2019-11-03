package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.models.marketinfo.BuyOrSell
import com.dllewellyn.denarii.models.marketinfo.BuySellPrice
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.Serializable

@Serializable
data class ApiValueResponse(
    val data: ApiBuyOrSell
)

@Serializable
data class ApiBuyOrSell(
    val base: String,
    val amount: String,
    val currency: String
)

fun ApiValueResponse.toCore(buyOrSell: BuyOrSell) = BuySellPrice(
    buyOrSell, BigDecimal.parseString(data.amount, 10), SupportedCurrency(data.currency), "coinbase"
)