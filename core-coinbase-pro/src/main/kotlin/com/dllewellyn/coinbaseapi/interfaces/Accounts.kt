package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository

abstract class Accounts : ReadOnlyRepository<List<Account>> {
    override suspend fun retrieveData() = getAccounts()
    abstract suspend fun getAccounts() : List<Account>
    abstract suspend fun getNonEmptyAccounts() : List<Account>
}

