package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.marketinfo.ProductTicker
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument

abstract class ProductTickerRetriever :  ReadOnlyRepositoryArgument<CurrencyPair, ProductTicker> {
    suspend fun getProductTicker(cryptoCurrency: CurrencyPair): ProductTicker = retrieveData(cryptoCurrency)
}