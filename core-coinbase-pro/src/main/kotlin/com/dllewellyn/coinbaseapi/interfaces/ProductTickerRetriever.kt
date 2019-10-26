package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.marketinfo.ProductTicker
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository

abstract class ProductTickerRetriever :  ReadOnlyPostRepository<CurrencyPair, ProductTicker> {
    suspend fun getProductTicker(cryptoCurrency: CurrencyPair): ProductTicker = retrieveData(cryptoCurrency)
}