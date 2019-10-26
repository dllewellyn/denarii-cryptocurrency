package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class OauthProvider(
    val access_token: String,
    val created_at: Double,
    val expires_in: Double,
    val refresh_token: String,
    val scope: String,
    val token_type: String
)