package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryNoArguments

abstract class Accounts : ReadOnlyRepositoryNoArguments<List<Account>> {
    override suspend fun retrieveData() = getAccounts()
    abstract suspend fun getAccounts() : List<Account>
    abstract suspend fun getNonEmptyAccounts() : List<Account>
}

