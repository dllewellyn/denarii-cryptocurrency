package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.api.models.ApiKeyAuth
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder

class RetrofitCoroutinesBuilder(sandbox: Boolean = false) : RetrofitApiBuilder(sandbox, false) {

    fun getProApi() =
        retrofitPro.build().create(CoinbaseProService::class.java)

    fun getProApiAuthentication(apiKeyAuth : ApiKeyAuth): CoinbaseProService {

        buildClientWith(apiKeyAuth.password, apiKeyAuth.apiKey, apiKeyAuth.secretKey)
        return retrofitProAuthenticated.build()
            .create(CoinbaseProService::class.java)
    }
}