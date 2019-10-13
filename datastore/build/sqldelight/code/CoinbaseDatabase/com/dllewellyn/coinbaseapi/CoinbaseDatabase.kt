package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.datastore.newInstance
import com.dllewellyn.coinbaseapi.datastore.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver

interface CoinbaseDatabase : Transacter {
  val productTickerQueries: ProductTickerQueries

  companion object {
    val Schema: SqlDriver.Schema
      get() = CoinbaseDatabase::class.schema

    operator fun invoke(driver: SqlDriver): CoinbaseDatabase =
        CoinbaseDatabase::class.newInstance(driver)}
}
