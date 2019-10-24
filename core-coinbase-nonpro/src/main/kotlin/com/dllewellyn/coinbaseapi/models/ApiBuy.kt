package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiBuy(
    val id: String,
    val resource: String,
    val resource_path: String
)