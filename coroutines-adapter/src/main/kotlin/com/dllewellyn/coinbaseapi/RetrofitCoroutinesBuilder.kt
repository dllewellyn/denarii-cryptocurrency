package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder
import com.dllewellyn.coinbaseapi.retrofit.services.CoinbaseProService

class RetrofitCoroutinesBuilder(sandbox: Boolean = false) : RetrofitApiBuilder(sandbox, false) {

    fun getProApi() =
        retrofitPro.build().create(CoinbaseProService::class.java)

    fun getProApiAuthentication(passphrase: String, apiKey: String, secretKey: String): CoinbaseProService {
        buildClientWith(passphrase, apiKey, secretKey)
        return retrofitProAuthenticated.build()
            .create(CoinbaseProService::class.java)
    }
}