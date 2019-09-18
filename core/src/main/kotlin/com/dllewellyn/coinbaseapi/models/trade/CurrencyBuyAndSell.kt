package com.dllewellyn.coinbaseapi.models.trade

data class CurrencyBuyAndSell(val currencyFrom : String, val currencyTo : String, val buy : Double, val sell : Double)