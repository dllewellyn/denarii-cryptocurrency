package com.dllewellyn.coinbaseapi.nonpro.interfaces

import com.dllewellyn.coinbaseapi.models.AccountData
import com.dllewellyn.coinbaseapi.models.BaseResponseApi
import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.denarii.repositories.DeleteRepository
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryNoArguments
import com.dllewellyn.denarii.repositories.UpdateRepository
import java.math.BigDecimal

abstract class Accounts : ReadOnlyRepositoryNoArguments<BaseResponseApi<AccountData>>,
    DeleteRepository<AccountData>, UpdateRepository<AccountData, String> {

    override suspend fun retrieveData() = getAllAccounts()

    abstract suspend fun getAllAccounts(): BaseResponseApi<AccountData>
    suspend fun getAllNonEmptyAccounts() =
        with(getAllAccounts()) {
            copy(data = data.filter {
                BigDecimal(it.balance.amount) > BigDecimal(0)
            })
        }

    abstract suspend fun setAccountAsPrimary(account : AccountData)
}