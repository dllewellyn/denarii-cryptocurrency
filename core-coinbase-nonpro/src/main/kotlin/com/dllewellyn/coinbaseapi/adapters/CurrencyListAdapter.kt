package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.interfaces.CurrencyList
import com.dllewellyn.coinbaseapi.models.ApiCurrencies
import com.dllewellyn.coinbaseapi.models.toCurrency
import io.ktor.client.request.get

class CurrencyListAdapter(private val client: InternalHttpClient) : CurrencyList() {
    override suspend fun retrieveData() =
        client.httpClient.get<ApiCurrencies>(client.url("currencies")).data.map { it.toCurrency() }
}