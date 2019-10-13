package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.CoinbaseProService
import com.dllewellyn.coinbaseapi.interfaces.Accounts
import com.dllewellyn.coinbaseapi.unwrap

class AccountsAdapter(private val retrofit: CoinbaseProService) : Accounts() {
    override suspend fun getAccounts() = retrofit.getAccounts().unwrap().map { it.toCore() }
    override suspend fun getNonEmptyAccounts() = getAccounts().filter { a -> a.balance.toInt() > 0 }
}