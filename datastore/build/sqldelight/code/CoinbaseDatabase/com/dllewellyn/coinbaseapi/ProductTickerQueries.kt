package com.dllewellyn.coinbaseapi

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String

interface ProductTickerQueries : Transacter {
  fun <T : Any> selectAllProductEntities(mapper: (
    ask: String,
    bid: String,
    price: String,
    time: String,
    size: String,
    tradeId: Long,
    volume: String
  ) -> T): Query<T>

  fun selectAllProductEntities(): Query<ProductTickerEntity>

  fun insertProductTicker(ProductTickerEntity: ProductTickerEntity)
}
