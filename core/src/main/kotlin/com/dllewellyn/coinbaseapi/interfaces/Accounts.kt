package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.Account

interface Accounts {
    suspend fun getAccounts() : List<Account>
    suspend fun getNonEmptyAccounts() : List<Account>
}

