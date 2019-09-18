package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.extensions.unwrap
import com.dllewellyn.coinbaseapi.interfaces.Accounts
import com.dllewellyn.coinbaseapi.models.Account
import com.dllewellyn.coinbaseapi.retrofit.services.CoinbaseProService

class AccountsAdapter(private val retrofit: CoinbaseProService) : Accounts {
    override suspend fun getAccounts() = retrofit.getAccounts().unwrap().map { it.toCore() }
    override suspend fun getNonEmptyAccounts() = getAccounts().filter { a -> a.balance > 0 }
}