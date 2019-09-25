package com.dllewellyn.coinbaseapi.retrofit

import com.dllewellyn.coinbaseapi.interceptors.AuthenticationInterceptor
import com.dllewellyn.coinbaseapi.interceptors.ErrorInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class RetrofitApiBuilder(sandbox: Boolean = false, private val errorInterceptor: Boolean = true) {

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
        with(OkHttpClient.Builder()) {
            if (errorInterceptor) {
                addInterceptor(ErrorInterceptor())
            }
            build()
        }

    }

    private val baseRetrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .client(standardOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }
    protected val retrofit: Retrofit.Builder by lazy {
        baseRetrofit
            .baseUrl(coinbase)
    }

    protected val retrofitPro: Retrofit.Builder by lazy {
        baseRetrofit
            .baseUrl(coinbasePro)
    }


    protected val retrofitProAuthenticated: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(coinbasePro)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
    }

    private lateinit var okHttpClient: OkHttpClient

    protected fun buildClientWith(passphrase: String, apiKey: String, secretKey: String) {
        okHttpClient = with(OkHttpClient.Builder()) {
            if (errorInterceptor) {
                addInterceptor(ErrorInterceptor())
            }
            addInterceptor(AuthenticationInterceptor(passphrase, apiKey, secretKey))
            build()
        }

    }


}