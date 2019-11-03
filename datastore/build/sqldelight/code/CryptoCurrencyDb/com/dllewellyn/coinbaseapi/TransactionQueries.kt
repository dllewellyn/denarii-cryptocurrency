package com.dllewellyn.coinbaseapi

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.String

interface TransactionQueries : Transacter {
  fun <T : Any> selectAllTransactions(mapper: (
    accountId: String,
    amount: String,
    balance: String?,
    description: String?,
    id: String,
    status: String?,
    type: String,
    date: String,
    nativeCurrency: String?,
    nativeAmount: String?
  ) -> T): Query<T>

  fun selectAllTransactions(): Query<TransactionEntity>

  fun <T : Any> selectAllTransactionsForAccount(accountId: String, mapper: (
    accountId: String,
    amount: String,
    balance: String?,
    description: String?,
    id: String,
    status: String?,
    type: String,
    date: String,
    nativeCurrency: String?,
    nativeAmount: String?
  ) -> T): Query<T>

  fun selectAllTransactionsForAccount(accountId: String): Query<TransactionEntity>

  fun insertIntoTransactions(TransactionEntity: TransactionEntity)
}
