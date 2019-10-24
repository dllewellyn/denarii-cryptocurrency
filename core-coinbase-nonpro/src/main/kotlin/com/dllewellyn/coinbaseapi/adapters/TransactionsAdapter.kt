package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.models.ApiTransaction
import com.dllewellyn.coinbaseapi.models.ApiTransactionList
import com.dllewellyn.coinbaseapi.models.BaseResponseApi
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText

class TransactionsRetriever(private val client: InternalHttpClient) {

    suspend fun retrieveTransactions(accountId: String) = client.httpClient.get<HttpResponse>(
        client.url("accounts/$accountId/transactions")
    )
        .readText()
        .let {
            client.json.parse(BaseResponseApi.serializer(ApiTransaction.serializer()), it)
        }

}