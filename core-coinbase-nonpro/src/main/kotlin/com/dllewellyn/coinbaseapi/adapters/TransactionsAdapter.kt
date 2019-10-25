package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.models.ApiTransaction
import com.dllewellyn.coinbaseapi.models.ApiTransactionList
import com.dllewellyn.coinbaseapi.models.BaseResponseApi
import com.dllewellyn.coinbaseapi.models.account.Transaction
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText

class TransactionsRetriever(private val client: InternalHttpClient) : ReadOnlyPostRepository<String, List<Transaction>> {

    override suspend fun retrieveData(arg: String) = client.httpClient.get<HttpResponse>(
        client.url("accounts/$arg/transactions")
    )
        .readText()
        .let {
            client.json.parse(BaseResponseApi.serializer(ApiTransaction.serializer()), it)
        }
        .data
        .map {
            Transaction(
                amount = it.amount.amount,
                date = it.created_at,
                description = it.description,
                id = it.id,
                status = it.status,
                type = it.type
            )
        }
}