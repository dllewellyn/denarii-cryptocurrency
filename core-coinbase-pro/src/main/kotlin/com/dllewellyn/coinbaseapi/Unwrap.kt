package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.exceptions.ApiException
import retrofit2.Response

fun <T> Response<T>.unwrap() : T {
    errorBody()?.let {
        throw ApiException(it.string())
    }

    return body() ?: throw ApiException("Body is empty")
}