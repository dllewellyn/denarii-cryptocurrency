package com.dllewellyn.coinbaseapi.nonpro.interfaces

import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import com.dllewellyn.denarii.models.currency.SupportedCurrency

abstract class CurrencyList : ReadOnlyRepository<List<SupportedCurrency>> {
    suspend fun getCurrencyList() = retrieveData()
}