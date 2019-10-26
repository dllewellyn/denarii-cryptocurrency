package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiDetails(
    val subtitle: String,
    val title: String
)