package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.interfaces.CurrencyList
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder
import com.dllewellyn.coinbaseapi.retrofit.adapters.CurrencyListAdapter

object Api {

    private val retrofit : RetrofitApiBuilder by lazy {
        RetrofitApiBuilder()
    }

    fun currencies() : CurrencyList = CurrencyListAdapter(retrofit)
}