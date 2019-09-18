package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.RetrofitCoroutinesBuilder
import com.dllewellyn.coinbaseapi.extensions.unwrap
import com.dllewellyn.coinbaseapi.interfaces.CurrencyPairsBase
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair

class CurrencyPairAdapter(private val retrofitCoroutinesBuilder: RetrofitCoroutinesBuilder) : CurrencyPairsBase() {
    override suspend fun getCurrencyPairs(): List<CurrencyPair> {
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