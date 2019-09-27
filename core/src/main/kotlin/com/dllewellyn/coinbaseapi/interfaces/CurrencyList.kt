package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.ReadOnlyRepository
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency

abstract class CurrencyList : ReadOnlyRepository<List<SupportedCurrency>> {
    suspend fun getCurrencyList() = retrieveData()
}