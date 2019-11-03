package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.repositories.ReadOnlyRepository
import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.currency.SupportedCurrency

abstract class CurrencyPairsList : ReadOnlyRepository<List<CurrencyPair>> {
    suspend fun getCurrencyPairs() = retrieveData()

    suspend fun currencyPairsContaining(vararg coins: SupportedCurrency) =
        getCurrencyPairs()
            .filter { pair ->
                pair.containsCurrency(*coins)
            }


}