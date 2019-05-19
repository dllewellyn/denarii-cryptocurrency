package com.dllewellyn.coinbaseapi.retrofit

import com.dllewellyn.coinbaseapi.retrofit.interceptors.AuthenticationInterceptor
import com.dllewellyn.coinbaseapi.retrofit.models.*
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface CoinbaseServiceV2 {

    @GET("v2/currencies")
    fun getCurrencies(): Single<ApiCurrencies>

    @GET("v2/exchange-rates")
    fun getExchangeRates(@Query("cryptoCurrency") currency: String): Single<ApiCurrencyRates>

    @GET("v2/prices/{currency_pair}/buy")
    fun getBuyPrice(@Path("currency_pair") currencyPair: String): Single<ApiValueResponse>

    @GET("v2/prices/{currency_pair}/sell")
    fun getSellPrice(@Path("currency_pair") currencyPair: String): Single<ApiValueResponse>

}

interface CoinbaseProService {
    @GET("products")
    fun getProducts(): Single<List<ApiProduct>>

    @GET("accounts")
    fun getAccounts(): Single<List<ApiAccount>>

    @GET("accounts/{account_id}")
    fun getSingleAccount(@Path("account_id") accountId: String): Single<List<ApiAccount>>

    @POST("orders")
    fun placeNewOrder(@Body apiBuyOrder: ApiBuyOrder): Single<ApiOrderResponse>
}

class RetrofitApiBuilder(sandbox: Boolean = false) {

    companion object {
        const val coinbase = "https://api.coinbase.com/"
    }

    private val coinbasePro =
        if (!sandbox) {
            "https://api.pro.coinbase.com/"
        } else {
            "https://api-public.sandbox.pro.coinbase.com"
        }


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(coinbase)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitPro: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(coinbasePro)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private val retrofitProAuthenticated: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(coinbasePro)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private lateinit var okHttpClient: OkHttpClient

    private fun buildClientWith(passphrase: String, apiKey: String, secretKey: String) {
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthenticationInterceptor(passphrase, apiKey, secretKey))
            .build()
    }

    fun getApi() = retrofit.create(CoinbaseServiceV2::class.java)
    fun getProApi() = retrofitPro.create(CoinbaseProService::class.java)

    fun getProApiAuthentication(passphrase: String, apiKey: String, secretKey: String): CoinbaseProService {
        buildClientWith(passphrase, apiKey, secretKey)
        return retrofitProAuthenticated.create(CoinbaseProService::class.java)
    }


}