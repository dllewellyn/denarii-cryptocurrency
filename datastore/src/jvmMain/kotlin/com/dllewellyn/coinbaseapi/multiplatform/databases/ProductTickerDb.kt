package com.dllewellyn.coinbaseapi.multiplatform.databases

import com.dllewellyn.coinbaseapi.multiplatform.base.BaseDb
import com.dllewellyn.coinbaseapi.multiplatform.toCore
import com.dllewellyn.coinbaseapi.multiplatform.toEntity
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import com.dllewellyn.coinbaseapi.repositories.WriteRepository
import com.dllewellyn.denarii.models.marketinfo.ProductTicker

class ProductTickerDb : BaseDb<List<ProductTicker>>(), ReadOnlyRepository<List<ProductTicker>>,
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