package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository

abstract class CurrencyPairsList : ReadOnlyRepository<List<CurrencyPair>> {
    suspend fun getCurrencyPairs() = retrieveData()

    suspend fun currencyPairsContaining(vararg coins: SupportedCurrency) =
        getCurrencyPairs()
            .filter { pair ->
                pair.containsCurrency(*coins)
            }


}