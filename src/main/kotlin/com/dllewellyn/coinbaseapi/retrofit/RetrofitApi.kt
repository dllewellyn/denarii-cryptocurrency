package com.dllewellyn.coinbaseapi.retrofit

import com.dllewellyn.coinbaseapi.retrofit.models.ApiCurrencies
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET

interface CoinbaseServiceV2 {

    @GET("v2/currencies")
    fun getCurrencies() : Single<ApiCurrencies>
}

class RetrofitApiBuilder {

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.coinbase.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getCurrencies() = retrofit.create(CoinbaseServiceV2::class.java)

}