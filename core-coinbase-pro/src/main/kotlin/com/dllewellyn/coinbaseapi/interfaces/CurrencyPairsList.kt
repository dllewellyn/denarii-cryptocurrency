package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryNoArguments

abstract class CurrencyPairsList : ReadOnlyRepositoryNoArguments<List<CurrencyPair>> {
    suspend fun getCurrencyPairs() = retrieveData()

    suspend fun currencyPairsContaining(vararg coins: SupportedCurrency) =
        getCurrencyPairs()
            .filter { pair ->
                pair.containsCurrency(*coins)
            }


}