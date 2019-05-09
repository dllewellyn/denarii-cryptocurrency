package com.dllewellyn.coinbaseapi.retrofit

import com.dllewellyn.coinbaseapi.retrofit.models.ApiProduct
import com.dllewellyn.coinbaseapi.retrofit.models.ApiCurrencies
import com.dllewellyn.coinbaseapi.retrofit.models.ApiCurrencyRates
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinbaseServiceV2 {

    @GET("v2/currencies")
    fun getCurrencies() : Single<ApiCurrencies>

    @GET("v2/exchange-rates")
    fun getExchangeRates(@Query("cryptoCurrency") currency : String) : Single<ApiCurrencyRates>

    @GET("v2/prices/{currency_pair}/buy")
    fun getBuyPrice(@Path("currency_pair") currencyPair : String)
}

interface CoinbaseProService {
    @GET("products")
    fun getProducts() : Single<List<ApiProduct>>
}

class RetrofitApiBuilder {

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.coinbase.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitPro : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.pro.coinbase.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApi() = retrofit.create(CoinbaseServiceV2::class.java)
    fun getProApi() = retrofitPro.create(CoinbaseProService::class.java)

}