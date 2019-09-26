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
            "uuid"
        )
        assertEquals(1, CalculateMaxTrade { account }.calculateBuySize(BigDecimal(100)))
    }
}