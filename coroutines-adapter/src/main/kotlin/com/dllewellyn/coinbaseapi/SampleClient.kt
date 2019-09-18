package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.extensions.unwrap
import kotlinx.coroutines.*

fun main() {
    GlobalScope.launch {
        println(RetrofitCoroutinesBuilder(true).getApi().getCurrencies().unwrap())
    }

    readLine()

}