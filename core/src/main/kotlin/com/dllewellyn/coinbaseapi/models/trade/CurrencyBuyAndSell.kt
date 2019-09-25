package com.dllewellyn.coinbaseapi.models.trade

import java.math.BigInteger

data class CurrencyBuyAndSell(val currencyFrom : String, val currencyTo : String, val buy : BigInteger, val sell : BigInteger)