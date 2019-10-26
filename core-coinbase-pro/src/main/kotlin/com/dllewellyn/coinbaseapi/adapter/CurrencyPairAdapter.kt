package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.RetrofitCoroutinesBuilder
import com.dllewellyn.coinbaseapi.unwrap
import com.dllewellyn.coinbaseapi.interfaces.CurrencyPairsList
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair

class CurrencyPairAdapter(private val retrofitCoroutinesBuilder: RetrofitCoroutinesBuilder) : CurrencyPairsList() {

    override suspend fun retrieveData(): List<CurrencyPair> {
        return retrofitCoroutinesBuilder.getProApi()
            .getProducts().unwrap().map { product ->
                CurrencyPair(
                    product.baseCurrency,
                    product.baseMaxSize,
                    product.baseMinSize,
                    product.id,
                    product.quoteCurrency,
                    product.quoteIncrement
                )

            }
    }
}