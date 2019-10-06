package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.interfaces.Accounts
import com.dllewellyn.coinbaseapi.models.UserAccountApi
import io.ktor.client.request.get

class AccountsAdapter(private val client: InternalHttpClient) : Accounts {
    override suspend fun getAllAccounts() =
        client.httpClient.get<UserAccountApi>(client.url("accounts"))
}