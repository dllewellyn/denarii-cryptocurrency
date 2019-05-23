package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import io.reactivex.Observable
import io.reactivex.Single

interface CurrencyList {
    fun getCurrencies() : Observable<SupportedCurrency>
    fun getCurrencyList() : Single<List<SupportedCurrency>>
}