package com.dllewellyn.coinbaseapi.datastore

import com.dllewellyn.coinbaseapi.CoinbaseDatabase
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

internal val KClass<CoinbaseDatabase>.schema: SqlDriver.Schema
  get() = CoinbaseDatabaseImpl.Schema

internal fun KClass<CoinbaseDatabase>.newInstance(driver: SqlDriver): CoinbaseDatabase =
    CoinbaseDatabaseImpl(driver)

private class CoinbaseDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), CoinbaseDatabase {
  override val productTickerQueries: ProductTickerQueriesImpl = ProductTickerQueriesImpl(this,
      driver)

  object Schema : SqlDriver.Schema {
    override val version: Int
      get() = 1

    override fun create(driver: SqlDriver) {
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

private class ProductTickerQueriesImpl(
  private val database: CoinbaseDatabaseImpl,
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
