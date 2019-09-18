package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder
import com.dllewellyn.coinbaseapi.retrofit.services.CoinbaseProService
import com.dllewellyn.coinbaseapi.retrofit.services.CoinbaseServiceV2

class RetrofitCoroutinesBuilder(private val sandbox : Boolean = false) : RetrofitApiBuilder(sandbox) {

    fun getApi() =
        retrofit.build().create(CoinbaseServiceV2::class.java)

    fun getProApi() =
        retrofitPro.build().create(CoinbaseProService::class.java)

    fun getProApiAuthentication(passphrase: String, apiKey: String, secretKey: String): CoinbaseProService {
        buildClientWith(passphrase, apiKey, secretKey)
        return retrofitProAuthenticated.build()
            .create(CoinbaseProService::class.java)
    }
}