package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.models.AccountData
import com.dllewellyn.coinbaseapi.models.BaseResponseApi
import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.coinbaseapi.nonpro.interfaces.Accounts
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent

class AccountsAdapter(private val client: InternalHttpClient) : Accounts() {

    override suspend fun update(thingToUpdate: AccountData, newValue: String) {
        client.httpClient.put<Account>(thingToUpdate.id) {
            body = TextContent("{\"name\": \"$newValue\"}", ContentType.Application.Json)
        }
    }

    override suspend fun delete(toDelete: AccountData) {
        client.httpClient.delete<HttpResponse>(client.url("accounts/${toDelete.id}"))
    }

    override suspend fun getAllAccounts() =
        client.httpClient.get<HttpResponse>(client.url("accounts"))
            .readText()
            .let {
                client.json.parse(BaseResponseApi.serializer(AccountData.serializer()), it)
            }
}