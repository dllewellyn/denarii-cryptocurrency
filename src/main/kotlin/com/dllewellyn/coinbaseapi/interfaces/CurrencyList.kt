package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.Currency
import io.reactivex.Observable
import io.reactivex.Single

interface CurrencyList {
    fun getCurrencies() : Observable<Currency>
    fun getCurrencyList() : Single<List<Currency>>
}