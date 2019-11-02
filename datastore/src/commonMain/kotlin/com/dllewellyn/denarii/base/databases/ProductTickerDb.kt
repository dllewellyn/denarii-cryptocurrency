package com.dllewellyn.denarii.base.databases

import com.dllewellyn.denarii.base.BaseDb
import com.dllewellyn.denarii.base.toCore
import com.dllewellyn.denarii.base.toEntity
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import com.dllewellyn.coinbaseapi.repositories.WriteRepository
import com.dllewellyn.denarii.models.marketinfo.ProductTicker
import com.squareup.sqldelight.db.SqlDriver

class ProductTickerDb(val sqlDriver: SqlDriver) : BaseDb<List<ProductTicker>>(sqlDriver), ReadOnlyRepository<List<ProductTicker>>,
    WriteRepository<List<ProductTicker>> {

    override suspend fun retrieveData() =
        database.productTickerQueries
            .selectAllProductEntities()
            .executeAsList()
            .map {
                it.toCore()
            }

    override suspend fun write(value: List<ProductTicker>) {
        value.forEach { database.productTickerQueries.insertProductTicker(it.toEntity()) }
    }
}