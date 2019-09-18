package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.CurrencyPairsBase
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.retrofit.RetrofitRxApiBuilder

class CurrencyPairAdapter(private val retrofit: RetrofitRxApiBuilder) : CurrencyPairsBase() {
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
}