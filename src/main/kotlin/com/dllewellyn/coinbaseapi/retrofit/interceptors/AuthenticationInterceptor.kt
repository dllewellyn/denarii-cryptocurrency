package com.dllewellyn.coinbaseapi.retrofit.interceptors

import com.dllewellyn.coinbaseapi.websocket.internal.authentication.SignatureGeneration
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.util.*


class AuthenticationInterceptor(
    private val passphrase: String,
    private val apiKey: String,
    private val secretKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = Calendar.getInstance(TimeZone.getTimeZone("UTC")).toInstant().epochSecond

        val requestBuffer = Buffer()
        val body = chain.request().body()?.let {
            it.writeTo(requestBuffer)
            requestBuffer.readUtf8()
        }

        val generated = SignatureGeneration(
            timestamp,
            chain.request().method(),
            chain.request().url().encodedPath(),
            body,
            secretKey
        ).generate()

        return chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("CB-ACCESS-KEY", apiKey)
                    .addHeader("CB-ACCESS-TIMESTAMP", timestamp.toString())
                    .addHeader("CB-ACCESS-PASSPHRASE", passphrase)
                    .addHeader("CB-ACCESS-SIGN", generated)
                    .build()
            )
        }
    }
}
