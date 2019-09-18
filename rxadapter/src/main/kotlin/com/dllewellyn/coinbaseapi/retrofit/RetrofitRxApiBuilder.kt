package com.dllewellyn.coinbaseapi.retrofit

import com.dllewellyn.coinbaseapi.retrofit.services.CoinbaseProService
import com.dllewellyn.coinbaseapi.retrofit.services.CoinbaseServiceV2
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RetrofitRxApiBuilder(sandbox: Boolean = false) : RetrofitApiBuilder(sandbox) {

    fun getApi() =
        retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(CoinbaseServiceV2::class.java)

    fun getProApi() =
        retrofitPro.addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(CoinbaseProService::class.java)

    fun getProApiAuthentication(passphrase: String, apiKey: String, secretKey: String): CoinbaseProService {
        buildClientWith(passphrase, apiKey, secretKey)
        return retrofitProAuthenticated.addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            .create(CoinbaseProService::class.java)
    }
}