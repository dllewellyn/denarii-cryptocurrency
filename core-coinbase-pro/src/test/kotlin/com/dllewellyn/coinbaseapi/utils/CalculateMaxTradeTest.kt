package com.dllewellyn.coinbaseapi.utils

import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.utils.CalculateMaxTrade
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import junit.framework.Assert.assertEquals
import org.junit.Test

class CalculateMaxTradeTest {

    @Test
    fun `calculate max trade example one`() {

        // Given £100. If I want to buy ETH at a limit order of £100
        val account = Account(
            SupportedCurrency("GBP"),
            BigDecimal.fromInt(100),
            BigDecimal.fromInt(100),
            BigDecimal.fromInt(0),
            "uuid",
            "coinbase-pro"
        )
        assertEquals(BigDecimal.fromInt(1), CalculateMaxTrade { account }.calculateMaxTrade(BigDecimal.fromInt(100)))
    }

    @Test
    fun `calculate max trade example two`() {

        // Given £100. If I want to buy ETH at a limit order of £100
        val account = Account(
            SupportedCurrency("GBP"),
            BigDecimal.fromInt(100),
            BigDecimal.fromInt(100),
            BigDecimal.fromInt(0),
            "uuid",
            "coinbase-pro"
        )
        assertEquals(
            BigDecimal.fromDouble(0.5),
            CalculateMaxTrade { account }.calculateMaxTrade(BigDecimal.fromInt(50))
        )
    }

    @Test
    fun `calculate max trade example three`() {

        // Given £100. If I want to buy ETH at a limit order of £100
        val account = Account(
            SupportedCurrency("GBP"),
            BigDecimal.fromInt(100),
            BigDecimal.fromInt(100),
            BigDecimal.fromInt(0),
            "uuid",
            "coinbase-pro"
        )
        assertEquals(BigDecimal.fromInt(2), CalculateMaxTrade { account }.calculateMaxTrade(BigDecimal.fromInt(200)))
    }

    @Test
    fun `calculate max trade example four`() {

        // Given £100. If I want to buy ETH at a limit order of £100
        val account = Account(
            SupportedCurrency("GBP"),
            BigDecimal.fromInt(100),
            BigDecimal.fromInt(100),
            BigDecimal.fromInt(0),
            "uuid",
            "coinbase-pro"
        )
        assertEquals(BigDecimal.fromDouble(1.5), CalculateMaxTrade { account }.calculateMaxTrade(BigDecimal.fromInt(150)))
    }
}