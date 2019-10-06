package com.dllewellyn.coinbaseapi.models


import kotlinx.serialization.Serializable

@Serializable
data class WarningApi(
    val id: String,
    val message: String,
    val url: String
)