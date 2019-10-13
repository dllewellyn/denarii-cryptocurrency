package com.dllewellyn.coinbaseapi.utils

import com.dllewellyn.coinbaseapi.models.Account
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class CalculateMaxTradeTest {

    @Test
    fun `calculate max trade example one`() {

        // Given £100. If I want to buy ETH at a limit order of £100
        val account = Account(
            SupportedCurrency("GBP"),
            BigDecimal(100),
            BigDecimal(100),
            BigDecimal(0),
            "uuid",
            "coinbase-pro"
        )
        assertEquals(BigDecimal(1), CalculateMaxTrade { account }.calculateMaxTrade(BigDecimal(100)))
    }

    @Test
    fun `calculate max trade example two`() {

        // Given £100. If I want to buy ETH at a limit order of £100
        val account = Account(
            SupportedCurrency("GBP"),
            BigDecimal(100),
            BigDecimal(100),
            BigDecimal(0),
            "uuid",
            "coinbase-pro"
        )
        assertEquals(BigDecimal(0.5), CalculateMaxTrade { account }.calculateMaxTrade(BigDecimal(50)))
    }

    @Test
    fun `calculate max trade example three`() {

        // Given £100. If I want to buy ETH at a limit order of £100
        val account = Account(
            SupportedCurrency("GBP"),
            BigDecimal(100),
            BigDecimal(100),
            BigDecimal(0),
            "uuid",
            "coinbase-pro"
        )
        assertEquals(BigDecimal(2), CalculateMaxTrade { account }.calculateMaxTrade(BigDecimal(200)))
    }

    @Test
    fun `calculate max trade example four`() {

        // Given £100. If I want to buy ETH at a limit order of £100
        val account = Account(
            SupportedCurrency("GBP"),
            BigDecimal(100),
            BigDecimal(100),
            BigDecimal(0),
            "uuid",
            "coinbase-pro"
        )
        assertEquals(BigDecimal(1.5), CalculateMaxTrade { account }.calculateMaxTrade(BigDecimal(150)))
    }
}