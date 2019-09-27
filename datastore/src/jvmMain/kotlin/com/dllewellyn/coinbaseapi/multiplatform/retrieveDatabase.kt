package com.dllewellyn.coinbaseapi.multiplatform

import com.dllewellyn.coinbaseapi.ProductTickerEntity
import com.dllewellyn.coinbaseapi.models.marketinfo.ProductTicker
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver.Companion.IN_MEMORY
import java.math.BigDecimal

actual fun retrieveDatabase(): SqlDriver = JdbcSqliteDriver(IN_MEMORY)

actual fun ProductTickerEntity.toCore() = ProductTicker(
    BigDecimal(ask),
    BigDecimal(bid),
    price,
    size,
    time,
    tradeId.toInt(),
    volume
)

actual fun ProductTicker.toEntity(): ProductTickerEntity = ProductTickerEntity.Impl(
    ask.toPlainString(),
    bid.toPlainString(),
    price,
    size,
    time,
    tradeId.toLong(),
    volume
)