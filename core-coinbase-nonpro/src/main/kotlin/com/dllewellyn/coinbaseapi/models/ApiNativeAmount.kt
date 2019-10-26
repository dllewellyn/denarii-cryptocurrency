package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiNativeAmount(
    val amount: String,
    val currency: String
)