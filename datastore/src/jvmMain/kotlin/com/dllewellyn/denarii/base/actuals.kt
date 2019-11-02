package com.dllewellyn.denarii.base

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

fun retrieveDatabase(): SqlDriver = JdbcSqliteDriver("jdbc:sqlite:./Crypto.db")