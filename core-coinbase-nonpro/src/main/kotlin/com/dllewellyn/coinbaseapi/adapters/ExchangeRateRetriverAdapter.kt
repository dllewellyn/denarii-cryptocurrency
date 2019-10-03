package com.dllewellyn.coinbaseapi.adapters

//package com.dllewellyn.coinbaseapi
//
//import com.dllewellyn.coinbaseapi.RetrofitCoroutinesBuilder
//import com.dllewellyn.coinbaseapi.api.models.toCore
//import com.dllewellyn.coinbaseapi.extensions.unwrap
//import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
//import com.dllewellyn.coinbaseapi.models.ExchangeRates
//import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
//import java.math.BigDecimal
//
//
//class ExchangeRateRetriverAdapter(private val retrofitCoroutinesBuilder: RetrofitCoroutinesBuilder) :
//    ExchangeRateRetriver() {
//
//    override suspend fun retrieveData(arg: CryptoCurrency) =
//        retrofitCoroutinesBuilder.getApi().getExchangeRates(arg.str).unwrap().let {
//            ExchangeRates(
//                arg,
//                it.data.rates.mapValues { rate -> BigDecimal(rate.value) })
//        }
//
//}