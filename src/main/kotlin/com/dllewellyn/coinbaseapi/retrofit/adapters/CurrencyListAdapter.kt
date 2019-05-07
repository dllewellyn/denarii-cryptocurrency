package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.CurrencyList
import com.dllewellyn.coinbaseapi.models.SupportedCurrency
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder
import com.dllewellyn.coinbaseapi.retrofit.models.toCurrency
import io.reactivex.Observable
import io.reactivex.Single

class CurrencyListAdapter(val retrofitApiBuilder: RetrofitApiBuilder) : CurrencyList {

    override fun getCurrencies(): Observable<SupportedCurrency> = getCurrencyList()
        .flattenAsObservable { x -> x }

    override fun getCurrencyList(): Single<List<SupportedCurrency>> = retrofitApiBuilder.getApi().getCurrencies()
        .map { it.data }
        .map { it.map { c -> c.toCurrency() } }
}