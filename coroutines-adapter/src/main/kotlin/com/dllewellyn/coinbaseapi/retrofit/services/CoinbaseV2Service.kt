package com.dllewellyn.coinbaseapi.retrofit.services

import com.dllewellyn.coinbaseapi.api.models.ApiCurrencies
import com.dllewellyn.coinbaseapi.api.models.ApiCurrencyRates
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinbaseServiceV2 {

    @GET("v2/currencies")
    suspend fun getCurrencies(): Response<ApiCurrencies>

    @GET("v2/exchange-rates")
    suspend fun getExchangeRates(@Query("cryptoCurrency") currency: String): Response<ApiCurrencyRates>
}
