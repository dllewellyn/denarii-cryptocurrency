package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.coinbaseapi.models.account.Transaction
import com.dllewellyn.coinbaseapi.models.toCore
import com.dllewellyn.coinbaseapi.nonpro.interfaces.Accounts
import com.dllewellyn.denarii.repositories.DeleteRepository
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryNoArguments
import io.ktor.client.request.delete
import io.ktor.client.response.HttpResponse

class AccountsCoreAdapter(
    private val accounts: Accounts,
    private val transactionRetriever: ReadOnlyRepositoryArgument<String, List<Transaction>>,
    private val client: InternalHttpClient
) : ReadOnlyRepositoryNoArguments<List<Account>>, DeleteRepository<Account> {

    override suspend fun delete(toDelete: Account) {
        client.httpClient.delete<HttpResponse>(client.url("accounts/${toDelete.uid}"))
    }

    override suspend fun retrieveData() = accounts.getAllNonEmptyAccounts().data.map {
        with(it.toCore()) {
            this.copy(transactions = transactionRetriever.retrieveData(it.id))
        }
    }
}