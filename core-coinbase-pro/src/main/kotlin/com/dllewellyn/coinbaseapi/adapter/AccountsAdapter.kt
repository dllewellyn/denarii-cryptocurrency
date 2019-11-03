package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.CoinbaseProService
import com.dllewellyn.coinbaseapi.interfaces.Accounts
import com.dllewellyn.coinbaseapi.models.account.Transaction
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import com.dllewellyn.coinbaseapi.unwrap

class AccountsAdapter(
    private val retrofit: CoinbaseProService,
    private val transactionRetriever: ReadOnlyPostRepository<String, List<Transaction>>? = null
) : Accounts() {
    override suspend fun getAccounts() =
        retrofit.getAccounts().unwrap().map { account ->

            transactionRetriever?.let {
                account.toCore().copy(
                    transactions = it.retrieveData(account.id)
                )
            } ?: account.toCore()
        }

    override suspend fun getNonEmptyAccounts() = getAccounts().filter { a -> a.balance > 0 }
}