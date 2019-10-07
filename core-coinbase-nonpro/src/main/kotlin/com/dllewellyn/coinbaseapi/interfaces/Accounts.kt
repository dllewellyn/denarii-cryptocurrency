package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.UserAccountApi
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import java.math.BigDecimal

abstract class Accounts : ReadOnlyRepository<UserAccountApi> {
    override suspend fun retrieveData() = getAllAccounts()
    abstract suspend fun getAllAccounts(): UserAccountApi
    suspend fun getAllNonEmptyAccounts() =
        with(getAllAccounts()) {
            copy(data = data.filter {
                BigDecimal(it.balance.amount) > BigDecimal(0)
            })
        }
}