package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.abstract.AccountsBase
import com.dllewellyn.coinbaseapi.retrofit.services.CoinbaseProService

class AccountsAdapter(private val retrofit: CoinbaseProService) : AccountsBase() {
    override fun getAccounts() = retrofit.getAccounts().map { it.map { c ->  c.toCore() }}
}
