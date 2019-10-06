package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.UserAccountApi

interface Accounts {
    suspend fun getAllAccounts() : UserAccountApi
}