package com.denarii.uphold.test

import com.denarii.uphold.json
import com.denarii.uphold.models.CardApi
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.serializer.SupportedCurrencySerializer
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import junit.framework.Assert.assertEquals
import org.junit.Test

class TestCardObject {

    @Test
    fun `test that a card object can serialise`() {
        ("{\n" +
                "  \"address\": {\n" +
                "    \"bitcoin\": \"ms22VBPSahNTxHZNkYo2d4Rmw1Tgfx6ojr\"\n" +
                "  },\n" +
                "  \"available\": \"146.38\",\n" +
                "  \"balance\": \"146.38\",\n" +
                "  \"currency\": \"EUR\",\n" +
                "  \"id\": \"bc9b3911-4bc1-4c6d-ac05-0ae87dcfc9b3\",\n" +
                "  \"label\": \"EUR card\",\n" +
                "  \"lastTransactionAt\": \"2018-08-01T09:53:51.258Z\",\n" +
                "  \"normalized\": [{\n" +
                "    \"available\": \"170.96\",\n" +
                "    \"balance\": \"170.96\",\n" +
                "    \"currency\": \"USD\"\n" +
                "  }],\n" +
                "  \"settings\": {\n" +
                "    \"position\": 2,\n" +
                "    \"protected\": false,\n" +
                "    \"starred\": true\n" +
                "  }\n" +
                "}")
            .let {
                json.parse(CardApi.serializer(), it).let { result ->
                    assertEquals(BigDecimal.parseString("146.38", 10), result.available)
                    assertEquals(BigDecimal.parseString("146.38", 10), result.balance)
                    assertEquals("bc9b3911-4bc1-4c6d-ac05-0ae87dcfc9b3", result.id)
                    assertEquals("EUR card", result.label)
                    assertEquals(BigDecimal.parseString("170.96", 10), result.normalized.first().available)
                    assertEquals(BigDecimal.parseString("170.96", 10), result.normalized.first().balance)
                    assertEquals(SupportedCurrency("USD"), result.normalized.first().currency)
                }
            }
    }
}