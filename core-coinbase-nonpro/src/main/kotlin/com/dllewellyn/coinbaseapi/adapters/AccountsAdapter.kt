package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.models.AccountData
import com.dllewellyn.coinbaseapi.models.BaseResponseApi
import com.dllewellyn.coinbaseapi.nonpro.interfaces.Accounts
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText

class AccountsAdapter(private val client: InternalHttpClient) : Accounts() {
    override suspend fun getAllAccounts() =
        client.httpClient.get<HttpResponse>(client.url("accounts"))
            .readText()
            .let {
                client.json.parse(BaseResponseApi.serializer(AccountData.serializer()), it)
            }
}