package com.dllewellyn.coinbaseapi

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.String

interface AccountsQueries : Transacter {
  fun <T : Any> selectAllFromAccounts(mapper: (
    currencyValue: String,
    balance: String,
    available: String,
    hold: String,
    uid: String,
    provider: String
  ) -> T): Query<T>

  fun selectAllFromAccounts(): Query<AccountEntity>

  fun insertAccount(AccountEntity: AccountEntity)
}
