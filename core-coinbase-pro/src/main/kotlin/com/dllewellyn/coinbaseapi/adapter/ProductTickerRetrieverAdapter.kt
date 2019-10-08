package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.RetrofitCoroutinesBuilder
import com.dllewellyn.coinbaseapi.api.models.toCore
import com.dllewellyn.coinbaseapi.unwrap
import com.dllewellyn.coinbaseapi.interfaces.ProductTickerRetriever
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.marketinfo.ProductTicker

class ProductTickerRetrieverAdapter(private val retrofitCoroutinesBuilder: RetrofitCoroutinesBuilder) :
    ProductTickerRetriever() {

    override suspend fun retrieveData(arg: CurrencyPair): ProductTicker =
        retrofitCoroutinesBuilder.getProApi().getProductTicker(arg.id).unwrap().toCore()

}