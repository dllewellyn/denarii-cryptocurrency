package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.marketinfo.ProductTicker

abstract class ProductTickerRetriever :  ReadOnlyPostRepository<CurrencyPair, ProductTicker> {
    suspend fun getProductTicker(cryptoCurrency: CurrencyPair): ProductTicker = retrieveData(cryptoCurrency)
}