package com.dllewellyn.coinbaseapi.retrofit.services

import com.dllewellyn.coinbaseapi.api.models.ApiCurrencies
import com.dllewellyn.coinbaseapi.api.models.ApiCurrencyRates
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinbaseServiceV2 {

    @GET("v2/currencies")
    fun getCurrencies(): Single<ApiCurrencies>

    @GET("v2/exchange-rates")
    fun getExchangeRates(@Query("cryptoCurrency") currency: String): Single<ApiCurrencyRates>
}
