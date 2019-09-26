package com.dllewellyn.coinbaseapi.models.trade

import java.math.BigDecimal

data class CurrencyBuyAndSell(val currencyFrom : String, val currencyTo : String, val buy : BigDecimal, val sell : BigDecimal)