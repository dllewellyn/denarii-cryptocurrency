package com.dllewellyn.coinbaseapi.models

data class OauthProvider(
    val access_token: String,
    val created_at: Double,
    val expires_in: Double,
    val refresh_token: String,
    val scope: String,
    val token_type: String
)