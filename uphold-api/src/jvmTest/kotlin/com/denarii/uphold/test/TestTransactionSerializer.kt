package com.denarii.uphold.test

import com.denarii.uphold.enums.ApiTarget
import com.denarii.uphold.enums.CardType
import com.denarii.uphold.enums.FeeType
import com.denarii.uphold.enums.TransactionType
import com.denarii.uphold.json
import com.denarii.uphold.models.DestinationOrOrigin
import com.denarii.uphold.models.TransactionApi
import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test

class TestTransactionSerializer {

    @Test
    fun `test transaction serializer`() {
        ("{\n" +
                "  \"application\": null,\n" +
                "  \"createdAt\": \"2018-08-01T09:53:47.020Z\",\n" +
                "  \"denomination\": {\n" +
                "    \"amount\": \"5.00\",\n" +
                "    \"currency\": \"GBP\",\n" +
                "    \"pair\": \"GBPUSD\",\n" +
                "    \"rate\": \"1.31\"\n" +
                "  },\n" +
                "  \"destination\": {\n" +
                "    \"CardId\": \"bc9b3911-4bc1-4c6d-ac05-0ae87dcfc9b3\",\n" +
                "    \"amount\": \"5.57\",\n" +
                "    \"base\": \"5.61\",\n" +
                "    \"commission\": \"0.04\",\n" +
                "    \"currency\": \"EUR\",\n" +
                "    \"description\": \"Angel Rath\",\n" +
                "    \"fee\": \"0.00\",\n" +
                "    \"isMember\": true,\n" +
                "    \"node\": {\n" +
                "      \"id\": \"bc9b3911-4bc1-4c6d-ac05-0ae87dcfc9b3\",\n" +
                "      \"type\": \"card\",\n" +
                "      \"user\": {\n" +
                "        \"id\": \"21e65c4d-55e4-41be-97a1-ff38d8f3d945\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"rate\": \"0.85620\",\n" +
                "    \"type\": \"card\"\n" +
                "  },\n" +
                "  \"fees\": [{\n" +
                "    \"amount\": \"0.04\",\n" +
                "    \"currency\": \"EUR\",\n" +
                "    \"percentage\": \"0.65\",\n" +
                "    \"target\": \"destination\",\n" +
                "    \"type\": \"exchange\"\n" +
                "  }],\n" +
                "  \"id\": \"2c326b15-7106-48be-a326-06f19e69746b\",\n" +
                "  \"message\": null,\n" +
                "  \"network\": \"uphold\",\n" +
                "  \"normalized\": [{\n" +
                "    \"amount\": \"6.56\",\n" +
                "    \"commission\": \"0.05\",\n" +
                "    \"currency\": \"USD\",\n" +
                "    \"fee\": \"0.00\",\n" +
                "    \"rate\": \"1.00000\",\n" +
                "    \"target\": \"destination\"\n" +
                "  }],\n" +
                "  \"origin\": {\n" +
                "    \"CardId\": \"48ce2ac5-c038-4426-b2f8-a2bdbcc93053\",\n" +
                "    \"amount\": \"6.56\",\n" +
                "    \"base\": \"6.56\",\n" +
                "    \"commission\": \"0.00\",\n" +
                "    \"currency\": \"USD\",\n" +
                "    \"description\": \"Angel Rath\",\n" +
                "    \"fee\": \"0.00\",\n" +
                "    \"isMember\": true,\n" +
                "    \"node\": {\n" +
                "      \"id\": \"48ce2ac5-c038-4426-b2f8-a2bdbcc93053\",\n" +
                "      \"type\": \"card\",\n" +
                "      \"user\": {\n" +
                "        \"id\": \"21e65c4d-55e4-41be-97a1-ff38d8f3d945\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"rate\": \"1.16795\",\n" +
                "    \"sources\": [{\n" +
                "      \"amount\": \"6.56\",\n" +
                "      \"id\": \"3db4ef24-c529-421f-8e8f-eb9da1b9a582\"\n" +
                "    }],\n" +
                "    \"type\": \"card\"\n" +
                "  },\n" +
                "  \"params\": {\n" +
                "    \"currency\": \"USD\",\n" +
                "    \"margin\": \"0.65\",\n" +
                "    \"pair\": \"EURUSD\",\n" +
                "    \"progress\": \"1\",\n" +
                "    \"rate\": \"1.16795\",\n" +
                "    \"ttl\": 18000,\n" +
                "    \"type\": \"transfer\"\n" +
                "  },\n" +
                "  \"priority\": \"normal\",\n" +
                "  \"reference\": null,\n" +
                "  \"status\": \"completed\",\n" +
                "  \"type\": \"transfer\"\n" +
                "}").let {

            json.parse(TransactionApi.serializer(), it).let { transaction ->
                assertNull(transaction.application)
                assertNull(transaction.reference)

                assertEquals("2c326b15-7106-48be-a326-06f19e69746b", transaction.id)
                assertEquals("2018-08-01T09:53:47.020Z", transaction.createdAt)
                assertEquals(BigDecimal.fromDouble(5.00), transaction.denomination.amount)
                assertEquals(BigDecimal.fromDouble(1.31), transaction.denomination.rate)
                assertEquals(CurrencyPair.fromId("GBP-USD"), transaction.denomination.pair)
                assertEquals(SupportedCurrency("GBP"), transaction.denomination.currency)

                assertEquals("bc9b3911-4bc1-4c6d-ac05-0ae87dcfc9b3", transaction.destination.cardId)
                assertEquals(BigDecimal.parseString("5.57", 10), transaction.destination.amount)
                assertEquals(BigDecimal.parseString("5.61", 10), transaction.destination.base)
                assertEquals(BigDecimal.parseString("0.04", 10), transaction.destination.commission)
                assertEquals(BigDecimal.parseString("0.00", 10), transaction.destination.fee)
                assertEquals(SupportedCurrency("EUR"), transaction.destination.currency)
                assertEquals("Angel Rath", transaction.destination.description)
                assertEquals(true, transaction.destination.isMember)
                assertEquals(CardType.CARD, transaction.destination.type)
                assertEquals("bc9b3911-4bc1-4c6d-ac05-0ae87dcfc9b3", transaction.destination.node.id)
                assertEquals(CardType.CARD, transaction.destination.node.type)
                assertEquals(BigDecimal.parseString("0.85620", 10), transaction.destination.rate)
                assertEquals("21e65c4d-55e4-41be-97a1-ff38d8f3d945", transaction.destination.node.user.id)

                assertEquals(SupportedCurrency("EUR"), transaction.fees.first().currency)
                assertEquals(0.65, transaction.fees.first().percentage)

                assertEquals(ApiTarget.DESTINATION, transaction.fees.first().target)
                assertEquals(FeeType.EXCHANGE, transaction.fees.first().type)
                assertEquals("uphold", transaction.network)

                assertEquals(BigDecimal.fromDouble(6.56), transaction.normalized.first().amount)
                assertEquals(BigDecimal.fromDouble(0.05), transaction.normalized.first().commission)
                assertEquals(SupportedCurrency("USD"), transaction.normalized.first().currency)
                assertEquals(0, transaction.normalized.first().fee.toBigInteger().intValue())
                assertEquals(BigDecimal.fromDouble(1.0000), transaction.normalized.first().rate)
                assertEquals(ApiTarget.DESTINATION, transaction.normalized.first().target)

                assertEquals(TransactionType.TRANSFER, transaction.type)
            }

        }
    }
}