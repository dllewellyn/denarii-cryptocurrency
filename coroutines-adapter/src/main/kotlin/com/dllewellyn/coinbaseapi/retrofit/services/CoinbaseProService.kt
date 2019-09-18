package com.dllewellyn.coinbaseapi.retrofit.services

import com.dllewellyn.coinbaseapi.api.models.*
import retrofit2.Response
import retrofit2.http.*

interface CoinbaseProService {
    @GET("products")
    suspend fun getProducts(): Response<List<ApiProduct>>

    @GET("accounts")
    suspend fun getAccounts(): Response<List<ApiAccount>>

    @GET("accounts/{account_id}")
    suspend fun getSingleAccount(@Path("account_id") accountId: String): Response<List<ApiAccount>>

    @POST("orders")
    suspend fun placeNewOrder(@Body apiOrder: ApiOrder): Response<ApiOrderResponse>

    @GET("/products/{currency_pair}/ticker")
    suspend fun getTradeTick(@Path("currency_pair") currencyPair: String): Response<ApiTradeTick>

    @GET("orders")
    suspend fun getOpenOrders(): Response<List<ApiOrderResponse>>

    @DELETE("orders/{order_id}")
    suspend fun deleteOrder(@Path("order_id") orderId: String): Response<Unit>

    @DELETE("orders")
    suspend fun deleteAll(): Response<List<String>>

    @GET("orders/{order_id}")
    suspend fun getOrder(@Path("order_id") orderId: String): Response<ApiOrderResponse>

    @GET("/products/{product_id}/book")
    suspend fun getOrderbook(@Path("product_id") productId: String, @Query("level") level: Int): Response<ApiProductOrderBook>

}