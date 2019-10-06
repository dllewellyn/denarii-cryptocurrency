package com.dllewellyn.coinbaseapi.models


import kotlinx.serialization.Serializable

@Serializable
data class BalanceApi(
    val amount: String,
    val currency: String
)