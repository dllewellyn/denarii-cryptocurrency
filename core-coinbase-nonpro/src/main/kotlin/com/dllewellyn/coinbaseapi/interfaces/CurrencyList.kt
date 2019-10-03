package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository

abstract class CurrencyList : ReadOnlyRepository<List<SupportedCurrency>> {
    suspend fun getCurrencyList() = retrieveData()
}