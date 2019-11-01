package com.dllewellyn.coinbaseapi.multiplatform

import com.dllewellyn.coinbaseapi.AccountEntity
import com.dllewellyn.coinbaseapi.ProductTickerEntity
import com.dllewellyn.coinbaseapi.TransactionEntity
import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.coinbaseapi.models.account.Transaction
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.models.marketinfo.ProductTicker
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

fun retrieveDatabase(): SqlDriver = JdbcSqliteDriver("jdbc:sqlite:./Crypto.db")

fun ProductTickerEntity.toCore() = ProductTicker(
    BigDecimal.parseString(ask, 10),
    BigDecimal.parseString(bid, 10),
    BigDecimal.parseString(price, 10),
    size,
    time,
    tradeId.toInt(),
    volume
)

fun ProductTicker.toEntity(): ProductTickerEntity = ProductTickerEntity.Impl(
    ask.toStringExpanded(),
    bid.toStringExpanded(),
    price.toStringExpanded(),
    size,
    time,
    tradeId.toLong(),
    volume
)

fun Transaction.toEntity(accountId: String): TransactionEntity =
    TransactionEntity.Impl(
        accountId = accountId,
        date = date,
        description = description,
        id = id,
        status = status,
        type = type,
        balance = balance,
        amount = amount,
        nativeCurrency = nativeCurrency,
        nativeAmount = nativeAmount
    )

fun TransactionEntity.toCore() = Transaction(
    amount,
    balance,
    description,
    id,
    status,
    type,
    date,
    nativeCurrency,
    nativeAmount
)

fun Account.toEntity(): AccountEntity = AccountEntity.Impl(
    currencyValue.id,
    balance.toStringExpanded(),
    available?.toStringExpanded() ?: "",
    hold?.toStringExpanded() ?: "",
    uid,
    provider

)

fun AccountEntity.toCore() = Account(
    SupportedCurrency(currencyValue),
    BigDecimal.parseString(balance, 10),
    if (available == "") {
        null
    } else {
        BigDecimal.parseString(available, 10)
    },
    if (hold == "") {
        null
    } else {
        BigDecimal.parseString(hold, 10)
    },
    uid,
    provider
)