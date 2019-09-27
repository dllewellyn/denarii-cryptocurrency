package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.RetrofitCoroutinesBuilder
import com.dllewellyn.coinbaseapi.api.models.toCurrency
import com.dllewellyn.coinbaseapi.extensions.unwrap
import com.dllewellyn.coinbaseapi.interfaces.CurrencyList

class CurrencyListAdapter(private val coroutinesBuilder: RetrofitCoroutinesBuilder) : CurrencyList() {
    override suspend fun retrieveData() =
        coroutinesBuilder.getApi().getCurrencies().unwrap().data.map { it.toCurrency() }
}