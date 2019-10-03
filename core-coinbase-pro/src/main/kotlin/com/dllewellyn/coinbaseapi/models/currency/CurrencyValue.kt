package com.dllewellyn.coinbaseapi.models.currency

import java.math.BigDecimal

data class CurrencyValue(val currencyFrom : String, val currencyTo : String, val amount : BigDecimal)