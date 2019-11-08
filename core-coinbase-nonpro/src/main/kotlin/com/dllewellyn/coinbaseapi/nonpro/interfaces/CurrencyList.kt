package com.dllewellyn.coinbaseapi.nonpro.interfaces

import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryNoArguments

abstract class CurrencyList : ReadOnlyRepositoryNoArguments<List<SupportedCurrency>> {
    suspend fun getCurrencyList() = retrieveData()
}