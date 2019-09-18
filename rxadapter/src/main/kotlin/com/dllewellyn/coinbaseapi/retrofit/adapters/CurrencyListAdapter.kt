package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.api.models.toCurrency
import com.dllewellyn.coinbaseapi.interfaces.CurrencyList
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import com.dllewellyn.coinbaseapi.retrofit.RetrofitRxApiBuilder
import io.reactivex.Observable
import io.reactivex.Single

class CurrencyListAdapter(private val retrofitApiBuilder: RetrofitRxApiBuilder) : CurrencyList {

    override fun getCurrencies(): Observable<SupportedCurrency> = getCurrencyList()
        .flattenAsObservable { x -> x }

    override fun getCurrencyList(): Single<List<SupportedCurrency>> = retrofitApiBuilder.getApi().getCurrencies()
        .map { it.data }
        .map { it.map { c -> c.toCurrency() } }
}