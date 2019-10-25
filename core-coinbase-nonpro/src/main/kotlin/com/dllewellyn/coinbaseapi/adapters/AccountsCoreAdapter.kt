package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.coinbaseapi.models.account.Transaction
import com.dllewellyn.coinbaseapi.models.toCore
import com.dllewellyn.coinbaseapi.nonpro.interfaces.Accounts
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository

class AccountsCoreAdapter(
    private val accounts: Accounts,
    private val transactionRetriever: ReadOnlyPostRepository<String, List<Transaction>>
) : ReadOnlyRepository<List<Account>> {
    override suspend fun retrieveData() = accounts.getAllNonEmptyAccounts().data.map {
        with(it.toCore()) {
            this.copy(transactions = transactionRetriever.retrieveData(it.id))
        }
    }
}