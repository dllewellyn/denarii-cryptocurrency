package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency

interface CurrencyPairsList {
    suspend fun getCurrencyPairs(): List<CurrencyPair>
    suspend fun currencyPairsContaining(vararg coins: SupportedCurrency): List<CurrencyPair>
}

abstract class CurrencyPairsBase : CurrencyPairsList {
    override suspend fun currencyPairsContaining(vararg coins: SupportedCurrency) =
        getCurrencyPairs()
            .filter { pair ->
                pair.containsCurrency(*coins)
            }


}