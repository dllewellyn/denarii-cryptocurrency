package com.dllewellyn.coinbaseapi.retrofit

import com.dllewellyn.coinbaseapi.retrofit.models.ApiCurrencies
import com.dllewellyn.coinbaseapi.retrofit.models.ApiCurrencyRates
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinbaseServiceV2 {

    @GET("v2/currencies")
    fun getCurrencies() : Single<ApiCurrencies>

    @GET("v2/exchange-rates")
    fun getExchangeRates(@Query("currency") currency : String) : Single<ApiCurrencyRates>
}

class RetrofitApiBuilder {

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.coinbase.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApi() = retrofit.create(CoinbaseServiceV2::class.java)

}