package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.datastore.newInstance
import com.dllewellyn.coinbaseapi.datastore.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver

interface CryptoCurrencyDb : Transacter {
  val accountsQueries: AccountsQueries

  val productTickerQueries: ProductTickerQueries

  companion object {
    val Schema: SqlDriver.Schema
      get() = CryptoCurrencyDb::class.schema

    operator fun invoke(driver: SqlDriver): CryptoCurrencyDb =
        CryptoCurrencyDb::class.newInstance(driver)}
}
