package com.dllewellyn.coinbaseapi.retrofit.interceptors

import com.dllewellyn.coinbaseapi.exceptions.ApiException
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.Interceptor
import okhttp3.Response

data class ErrorMessage(@SerializedName("message") val message : String)

class ErrorInterceptor : Interceptor {

    val gson : Gson by lazy {
        Gson()
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = (chain.request()
            .let(chain::proceed))

        return when (response.code()) {
            400 -> response.body()?.let {
                throw ApiException(gson.fromJson(it.string(), ErrorMessage::class.java).message)
            } ?: response
            else -> response
        }

    }


}