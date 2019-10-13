package com.dllewellyn.coinbaseapi.datastore

import com.dllewellyn.coinbaseapi.AccountEntity
import com.dllewellyn.coinbaseapi.AccountsQueries
import com.dllewellyn.coinbaseapi.CryptoCurrencyDb
import com.dllewellyn.coinbaseapi.ProductTickerEntity
import com.dllewellyn.coinbaseapi.ProductTickerQueries
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<CryptoCurrencyDb>.schema: SqlDriver.Schema
  get() = CryptoCurrencyDbImpl.Schema

internal fun KClass<CryptoCurrencyDb>.newInstance(driver: SqlDriver): CryptoCurrencyDb =
    CryptoCurrencyDbImpl(driver)

private class CryptoCurrencyDbImpl(
  driver: SqlDriver
) : TransacterImpl(driver), CryptoCurrencyDb {
  override val accountsQueries: AccountsQueriesImpl = AccountsQueriesImpl(this, driver)

  override val productTickerQueries: ProductTickerQueriesImpl = ProductTickerQueriesImpl(this,
      driver)

  object Schema : SqlDriver.Schema {
    override val version: Int
      get() = 1

    override fun create(driver: SqlDriver) {
      driver.execute(null, """
          |CREATE TABLE AccountEntity (
          |    currencyValue TEXT NOT NULL,
          |    balance TEXT NOT NULL,
          |    available TEXT NOT NULL,
          |    hold TEXT NOT NULL,
          |    uid TEXT NOT NULL PRIMARY KEY,
          |    provider TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE ProductTickerEntity (
          |    ask TEXT NOT NULL,
          |    bid TEXT NOT NULL,
          |    price TEXT NOT NULL,
          |    time TEXT NOT NULL,
          |    size TEXT NOT NULL,
          |    tradeId INTEGER PRIMARY KEY,
          |    volume TEXT NOT NULL
          |)
          """.trimMargin(), 0)
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ) {
    }
  }
}

private class AccountsQueriesImpl(
  private val database: CryptoCurrencyDbImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AccountsQueries {
  internal val selectAllFromAccounts: MutableList<Query<*>> = copyOnWriteList()

  override fun <T : Any> selectAllFromAccounts(mapper: (
    currencyValue: String,
    balance: String,
    available: String,
    hold: String,
    uid: String,
    provider: String
  ) -> T): Query<T> = Query(53003298, selectAllFromAccounts, driver, "Accounts.sq",
      "selectAllFromAccounts", "SELECT * FROM AccountEntity") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!
    )
  }

  override fun selectAllFromAccounts(): Query<AccountEntity> =
      selectAllFromAccounts(AccountEntity::Impl)

  override fun insertAccount(AccountEntity: AccountEntity) {
    driver.execute(-1437552383, """INSERT INTO AccountEntity VALUES (?, ?, ?, ?, ?, ?)""", 6) {
      bindString(1, AccountEntity.currencyValue)
      bindString(2, AccountEntity.balance)
      bindString(3, AccountEntity.available)
      bindString(4, AccountEntity.hold)
      bindString(5, AccountEntity.uid)
      bindString(6, AccountEntity.provider)
    }
    notifyQueries(-1437552383, {database.accountsQueries.selectAllFromAccounts})
  }
}

private class ProductTickerQueriesImpl(
  private val database: CryptoCurrencyDbImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), ProductTickerQueries {
  internal val selectAllProductEntities: MutableList<Query<*>> = copyOnWriteList()

  override fun <T : Any> selectAllProductEntities(mapper: (
    ask: String,
    bid: String,
    price: String,
    time: String,
    size: String,
    tradeId: Long,
    volume: String
  ) -> T): Query<T> = Query(-1976475917, selectAllProductEntities, driver, "ProductTicker.sq",
      "selectAllProductEntities", "SELECT * FROM ProductTickerEntity") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getLong(5)!!,
      cursor.getString(6)!!
    )
  }

  override fun selectAllProductEntities(): Query<ProductTickerEntity> =
      selectAllProductEntities(ProductTickerEntity::Impl)

  override fun insertProductTicker(ProductTickerEntity: ProductTickerEntity) {
    driver.execute(2048340664, """INSERT INTO ProductTickerEntity VALUES (?, ?, ?, ?, ?, ?, ?)""",
        7) {
      bindString(1, ProductTickerEntity.ask)
      bindString(2, ProductTickerEntity.bid)
      bindString(3, ProductTickerEntity.price)
      bindString(4, ProductTickerEntity.time)
      bindString(5, ProductTickerEntity.size)
      bindLong(6, ProductTickerEntity.tradeId)
      bindString(7, ProductTickerEntity.volume)
    }
    notifyQueries(2048340664, {database.productTickerQueries.selectAllProductEntities})
  }
}
