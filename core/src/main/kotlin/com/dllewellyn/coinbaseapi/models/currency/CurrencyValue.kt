package com.dllewellyn.coinbaseapi.models.currency

import java.math.BigInteger

data class CurrencyValue(val currencyFrom : String, val currencyTo : String, val amount : BigInteger)