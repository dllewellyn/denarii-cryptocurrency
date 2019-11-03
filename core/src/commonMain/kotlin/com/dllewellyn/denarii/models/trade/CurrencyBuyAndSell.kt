package com.dllewellyn.denarii.models.trade

import com.ionspin.kotlin.bignum.decimal.BigDecimal

data class CurrencyBuyAndSell(val currencyFrom : String, val currencyTo : String, val buy : BigDecimal, val sell : BigDecimal)