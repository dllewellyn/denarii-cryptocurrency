package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.CurrencyPairsList
import com.dllewellyn.coinbaseapi.models.CurrencyPair
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder

class CurrencyPairAdapter(private val retrofit: RetrofitApiBuilder) : CurrencyPairsList {
    override fun getCurrencyPairs() = retrofit.getProApi()
        .getProducts().map {
            it.map { product ->
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

    override fun getCurrencyPairsObservable() =
            getCurrencyPairs()
                .flattenAsObservable { it }
}