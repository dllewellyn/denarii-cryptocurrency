package com.dllewellyn.coinbaseapi.nonpro.interfaces

import com.dllewellyn.coinbaseapi.models.AccountData
import com.dllewellyn.coinbaseapi.models.BaseResponseApi
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import java.math.BigDecimal

abstract class Accounts : ReadOnlyRepository<BaseResponseApi<AccountData>> {
    override suspend fun retrieveData() = getAllAccounts()

    abstract suspend fun getAllAccounts(): BaseResponseApi<AccountData>
    suspend fun getAllNonEmptyAccounts() =
        with(getAllAccounts()) {
            copy(data = data.filter {
                BigDecimal(it.balance.amount) > BigDecimal(0)
            })
        }
}