package com.dllewellyn.denarii.utils

import com.dllewellyn.coinbaseapi.models.account.Account
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode

/**
 * Calculate the max trade given the rate
 *
 * Pass in a function to retrieve the account that you want to buy or sell
 * then call 'calculate buy size' or 'calculate sell size' in order to retrieve the max order
 */
class CalculateMaxTrade(private val retrieveBuy: () -> Account) {

    fun calculateMaxTrade(proposedBuyLimit: BigDecimal): BigDecimal =
        proposedBuyLimit.divide(
            retrieveBuy().available ?: BigDecimal.ONE, decimalMode = DecimalMode(
            2, RoundingMode.ROUND_HALF_AWAY_FROM_ZERO
            )
        )
}