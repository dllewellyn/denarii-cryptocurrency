package com.dllewellyn.coinbaseapi.math

import com.dllewellyn.coinbaseapi.models.BuyOrSell
import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import com.dllewellyn.coinbaseapi.models.marketinfo.BuySellPrice
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.math.BigDecimal


class Account_valueKtTest {

    @Test
    fun `Test that account list is added together correctly`() = runBlockingTest {
        val currency = CryptoCurrency.GBP
        val repository = mockk<ReadOnlyPostRepository<CryptoCurrency, List<BuySellPrice>>>()

        every { runBlocking { repository.retrieveData(currency) } } returns
                listOf(
                    BuySellPrice(
                        BuyOrSell.SPOT,
                        amount = BigDecimal(0.3),
                        currency = SupportedCurrency("A"),
                        platform = ""
                    )
                )

        listOf(
            Account(
                currencyValue = SupportedCurrency("A"),
                balance = BigDecimal(1),
                provider = "coinbase",
                uid = "ADASD"
            ),
            Account(
                currencyValue = SupportedCurrency("A"),
                balance = BigDecimal(2),
                provider = "coinbase",
                uid = "ADASD"
            )
        )
            .currentValue(currency, repository).let {
                assertEquals(10, it.toInt())
            }
    }
}