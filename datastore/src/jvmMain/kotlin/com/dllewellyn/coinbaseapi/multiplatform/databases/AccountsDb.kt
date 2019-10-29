package com.dllewellyn.coinbaseapi.multiplatform.databases

import com.dllewellyn.coinbaseapi.multiplatform.base.BaseDb
import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.coinbaseapi.multiplatform.toCore
import com.dllewellyn.coinbaseapi.multiplatform.toEntity
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import com.dllewellyn.coinbaseapi.repositories.WriteRepository

class AccountsDb : BaseDb<List<Account>>(), ReadOnlyRepository<List<Account>>,
    WriteRepository<List<Account>> {

    override suspend fun write(value: List<Account>) {
        value.forEach { account ->
            database.accountsQueries
                .insertAccount(account.toEntity())

            account.transactions.map { transaction ->
                transaction.toEntity(account.uid)
            }.forEach { database.transactionQueries.insertIntoTransactions(it) }

        }
    }

    override suspend fun retrieveData() =
        database.accountsQueries.selectAllFromAccounts()
            .executeAsList()
            .map {
                it.toCore()
                    .copy(transactions = database.transactionQueries.selectAllTransactionsForAccount(it.uid).executeAsList().map { transaction ->
                        transaction.toCore()
                    })
            }
}