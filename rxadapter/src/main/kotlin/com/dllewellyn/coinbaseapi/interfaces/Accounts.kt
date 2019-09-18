package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.Account
import io.reactivex.Single

interface Accounts {
    fun getAccounts() : Single<List<Account>>
    fun getNonEmptyAccounts() : Single<List<Account>>
}

