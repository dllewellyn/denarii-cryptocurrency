package com.dllewellyn.coinbaseapi.abstract

import com.dllewellyn.coinbaseapi.interfaces.Accounts
import com.dllewellyn.coinbaseapi.models.Account
import io.reactivex.Single

abstract class AccountsBase : Accounts {
    override fun getNonEmptyAccounts() : Single<List<Account>> {
        return getAccounts().map {
            it.filter { a -> a.balance.toInt() > 0 }
        }
    }
}