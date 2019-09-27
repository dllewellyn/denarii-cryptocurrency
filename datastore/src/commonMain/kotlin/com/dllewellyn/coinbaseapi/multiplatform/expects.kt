package com.dllewellyn.coinbaseapi.multiplatform

import com.dllewellyn.coinbaseapi.ProductTickerEntity
import com.dllewellyn.coinbaseapi.models.marketinfo.ProductTicker
import com.squareup.sqldelight.db.SqlDriver

expect fun retrieveDatabase(): SqlDriver

expect fun ProductTicker.toEntity(): ProductTickerEntity
expect fun ProductTickerEntity.toCore(): ProductTicker