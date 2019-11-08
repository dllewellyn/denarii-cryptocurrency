package com.dllewellyn.denarii

import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.denarii.math.currentValue
import com.dllewellyn.denarii.models.currency.CryptoCurrency
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.models.marketinfo.BuyOrSell
import com.dllewellyn.denarii.models.marketinfo.BuySellPrice
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.math.BigInteger


class Account_valueKtTest {

    @Test
    fun `Test that account list is added together correctly`() = runBlockingTest {
        val currency = CryptoCurrency.GBP
        val repository = mockk<ReadOnlyRepositoryArgument<CryptoCurrency, List<BuySellPrice>>>()

        every { runBlocking { repository.retrieveData(currency) } } returns
                listOf(
                    BuySellPrice(
                        BuyOrSell.SPOT,
                        amount = BigDecimal.fromDouble(0.3),
                        currency = SupportedCurrency("A"),
                        platform = ""
                    )
                )

        listOf(
            Account(
                currencyValue = SupportedCurrency("A"),
                balance = BigDecimal.fromInt(1),
                provider = "coinbase",
                uid = "ADASD"
            ),
            Account(
                currencyValue = SupportedCurrency("A"),
                balance = BigDecimal.fromInt(2),
                provider = "coinbase",
                uid = "ADASD"
            )
        )
            .currentValue(currency, repository).let {
                assertEquals(10, it.toBigInteger().intValue())
            }
    }
}