package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.models.AccountData
import junit.framework.Assert.assertEquals
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.junit.Test

class AccountDataParsingTest {
    val json = Json(JsonConfiguration.Stable.copy(strictMode = false))

    @Test
    fun `account data parsing`() {
        with(json.parse(AccountData.serializer(), data)) {
            assertEquals("2bbf394c-193b-5b2a-9155-3b4732659ede", id)
            assertEquals("My Wallet", name)
            assertEquals(true, primary)
        }

    }


    val data = """{
  "id": "2bbf394c-193b-5b2a-9155-3b4732659ede",
  "name": "My Wallet",
  "primary": true,
  "type": "wallet",
  "currency": {
     "code": "BTC"
     "color": "Red?"
     "name": "Bitcoin"
     "sort_index": 0
     "type": "type"
     },
  "balance": {
      "amount": "39.59000000",
      "currency": "BTC"
  },
  "created_at": "2015-01-31T20:49:02Z",
  "updated_at": "2015-01-31T20:49:02Z",
  "resource": "account",
  "resource_path": "/v2/accounts/2bbf394c-193b-5b2a-9155-3b4732659ede"
}"""
}