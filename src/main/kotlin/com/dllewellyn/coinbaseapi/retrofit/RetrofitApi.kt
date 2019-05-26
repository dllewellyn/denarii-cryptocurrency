package com.dllewellyn.coinbaseapi.retrofit

import com.dllewellyn.coinbaseapi.retrofit.interceptors.AuthenticationInterceptor
import com.dllewellyn.coinbaseapi.retrofit.interceptors.ErrorInterceptor
import com.dllewellyn.coinbaseapi.retrofit.models.*
import io.reactivex.Completable
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
}

interface CoinbaseProService {
    @GET("products")
    fun getProducts(): Single<List<ApiProduct>>

    @GET("accounts")
    fun getAccounts(): Single<List<ApiAccount>>

    @GET("accounts/{account_id}")
    fun getSingleAccount(@Path("account_id") accountId: String): Single<List<ApiAccount>>

    @POST("orders")
    fun placeNewOrder(@Body apiOrder: ApiOrder): Single<ApiOrderResponse>

    @GET("/products/{currency_pair}/ticker")
    fun getTradeTick(@Path("currency_pair") currencyPair: String): Single<ApiTradeTick>

    @GET("orders")
    fun getOpenOrders() : Single<List<ApiOrderResponse>>

    @DELETE("orders/{order_id}")
    fun deleteOrder(@Path("order_id") orderId : String) : Completable

    @DELETE("orders")
    fun deleteAll() : Single<List<String>>

    @GET("orders/{order_id}")
    fun getOrder(@Path("order_id") orderId : String): Single<ApiOrderResponse>

    @GET("/products/{product_id}/book")
    fun getOrderbook(@Path("product_id") productId : String, @Query("level") level : Int) : Single<ApiProductOrderBook>

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

    private val standardOkHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ErrorInterceptor())
            .build()
    }

    private val baseRetrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .client(standardOkHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }
    private val retrofit: Retrofit by lazy {
        baseRetrofit
            .baseUrl(coinbase)
            .build()
    }

    private val retrofitPro: Retrofit by lazy {
        baseRetrofit
            .baseUrl(coinbasePro)
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
            .addInterceptor(ErrorInterceptor())
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