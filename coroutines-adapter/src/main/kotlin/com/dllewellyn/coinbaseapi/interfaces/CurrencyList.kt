package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency

interface CurrencyList {
    suspend fun getCurrencyList() : List<SupportedCurrency>
}