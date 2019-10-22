package com.dllewellyn.coinbaseapi.models

data class OauthProvider(
    val access_token: String,
    val created_at: Int,
    val expires_in: Int,
    val refresh_token: String,
    val scope: String,
    val token_type: String
)