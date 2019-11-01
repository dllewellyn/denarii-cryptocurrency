package com.denarii.uphold.test

import com.denarii.uphold.enums.CardType
import com.denarii.uphold.enums.Status
import com.denarii.uphold.json
import com.denarii.uphold.models.AccountApi
import junit.framework.Assert.assertEquals
import org.junit.Test

class TestSerializations {

    @Test
    fun `test parsing an account object`() {

        ("{" + "  \"billing\": {" +
                "    \"name\": \"Abigail Davis\"" +
                "  }," +
                "  \"brand\": \"visa\"," +
                "  \"currency\": \"USD\"," +
                "  \"id\": \"0874745c-f0bf-4973-a3d9-9832aeaae087\"," +
                "  \"label\": \"Savings Account\"," +
                "  \"status\": \"ok\"," +
                "  \"type\": \"card\"" + "}").let {
            json.parse(AccountApi.serializer(), it).let { transaction ->
                assertEquals("visa", transaction.brand)
                assertEquals("USD", transaction.currency)
                assertEquals("0874745c-f0bf-4973-a3d9-9832aeaae087", transaction.id)
                assertEquals("Savings Account", transaction.label)
                assertEquals(CardType.CARD, transaction.type)
                assertEquals(Status.OK, transaction.status)
            }
        }
    }

}