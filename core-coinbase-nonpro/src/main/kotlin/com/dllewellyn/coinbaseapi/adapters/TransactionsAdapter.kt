package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.models.AccountData
import com.dllewellyn.coinbaseapi.models.ApiTransaction
import com.dllewellyn.coinbaseapi.models.BaseResponseApi
import com.dllewellyn.coinbaseapi.nonpro.interfaces.Transactions
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText

class TransactionsAdapter(private val client: InternalHttpClient) : Transactions {

    override suspend fun retrieveData(arg: AccountData) = client.httpClient.get<HttpResponse>(
        client.url("accounts/$arg/transactions")
    )
        .readText()
        .let {
            client.json.parse(BaseResponseApi.serializer(ApiTransaction.serializer()), it)
        }
        .data
}