package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.models.Account
import com.dllewellyn.coinbaseapi.models.toCore
import com.dllewellyn.coinbaseapi.nonpro.interfaces.Accounts
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository

class AccountsCoreAdapter(val accounts: Accounts) : ReadOnlyRepository<List<Account>> {
    override suspend fun retrieveData() = accounts.getAllNonEmptyAccounts().data.map { it.toCore() }
}