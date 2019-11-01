package com.denarii.uphold.test

import com.denarii.uphold.json
import com.denarii.uphold.models.CurrencyPairApi
import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import junit.framework.Assert.assertEquals
import org.junit.Test

class TestCurrencyPairSerializer {

    @Test
    fun `currency pair serializer`() {
        ("{\n" +
                "  \"ask\": \"6420.05\",\n" +
                "  \"bid\": \"6419\",\n" +
                "  \"currency\": \"USD\",\n" +
                "  \"pair\": \"BTCUSD\"\n" +
                "}").let {
            json.parse(CurrencyPairApi.serializer(), it).let { pair ->
                assertEquals(BigDecimal.fromDouble(6420.05), pair.ask)
                assertEquals(BigDecimal.fromInt(6419), pair.bid)
                assertEquals(SupportedCurrency("USD"), pair.currency)
                assertEquals(CurrencyPair.fromId("BTC-USD"), pair.pair)
            }
        }
    }
}