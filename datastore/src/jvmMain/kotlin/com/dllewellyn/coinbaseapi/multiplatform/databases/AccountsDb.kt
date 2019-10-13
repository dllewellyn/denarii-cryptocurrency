package com.dllewellyn.coinbaseapi.multiplatform.databases

import com.dllewellyn.coinbaseapi.multiplatform.base.BaseDb
import com.dllewellyn.coinbaseapi.models.Account
import com.dllewellyn.coinbaseapi.multiplatform.toCore
import com.dllewellyn.coinbaseapi.multiplatform.toEntity
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import com.dllewellyn.coinbaseapi.repositories.WriteRepository

class AccountsDb : BaseDb<List<Account>>(), ReadOnlyRepository<List<Account>>,
    WriteRepository<List<Account>> {

    override suspend fun write(value: List<Account>) {
        value.forEach {
            database.accountsQueries
                .insertAccount(it.toEntity())
        }
    }

    override suspend fun retrieveData() =
        database.accountsQueries.selectAllFromAccounts()
            .executeAsList()
            .map {
                it.toCore()
            }
}