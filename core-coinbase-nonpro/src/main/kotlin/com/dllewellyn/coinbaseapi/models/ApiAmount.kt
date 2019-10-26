package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiAmount(
    val amount: String,
    val currency: String
)