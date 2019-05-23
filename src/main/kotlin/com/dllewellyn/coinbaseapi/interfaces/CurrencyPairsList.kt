package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import io.reactivex.Observable
import io.reactivex.Single

interface CurrencyPairsList {
    fun getCurrencyPairs(): Single<List<CurrencyPair>>
    fun getCurrencyPairsObservable(): Observable<CurrencyPair>
    fun currencyPairsContaining(vararg coins: SupportedCurrency): Single<List<CurrencyPair>>
}

abstract class CurrencyPairsBase : CurrencyPairsList {
    override fun currencyPairsContaining(vararg coins: SupportedCurrency) =
        getCurrencyPairs()
            .map {
                it.filter { pair ->
                    pair.containsCurrency(*coins)
                }
            }


    override fun getCurrencyPairsObservable() =
        getCurrencyPairs()
            .flattenAsObservable { it }
}