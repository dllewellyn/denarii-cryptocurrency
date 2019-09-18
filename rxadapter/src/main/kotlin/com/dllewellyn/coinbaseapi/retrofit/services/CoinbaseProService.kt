package com.dllewellyn.coinbaseapi.retrofit.services

import com.dllewellyn.coinbaseapi.api.models.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

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