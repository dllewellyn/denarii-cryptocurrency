package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.CurrencyPair
import io.reactivex.Observable
import io.reactivex.Single

interface CurrencyPairsList {
    fun getCurrencyPairs() : Single<List<CurrencyPair>>
    fun getCurrencyPairsObservable() : Observable<CurrencyPair>
}