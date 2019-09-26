package com.dllewellyn.coinbaseapi.utils

import com.dllewellyn.coinbaseapi.models.Account
import java.math.BigDecimal

/**
 * Calculate the max trade given the rate
 *
 * Pass in a function to retrieve the account that you want to buy or sell
 * then call 'calculate buy size' or 'calculate sell size' in order to retrieve the max order
 */
class CalculateMaxTrade(private val retrieveBuy: () -> Account) {

    fun calculateBuySize(proposedBuyLimit : BigDecimal) =
        retrieveBuy().available / proposedBuyLimit

}