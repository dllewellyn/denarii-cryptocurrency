package com.dllewellyn.denarii.base

import android.content.Context
import com.dllewellyn.coinbaseapi.CryptoCurrencyDb
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

fun retrieveDatabase(context: Context): SqlDriver = AndroidSqliteDriver(CryptoCurrencyDb.Schema, context, "test.db")
