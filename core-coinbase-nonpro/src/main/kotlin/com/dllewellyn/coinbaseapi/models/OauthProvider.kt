package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class OauthProvider(
    val access_token: String,
    val created_at: Long,
    val expires_in: Long,
    val refresh_token: String,
    val scope: String,
    val token_type: String
)

fun OauthProvider.toMap() = mutableMapOf<String, Any>().apply {
    put("access_token", access_token)
    put("created_at", created_at)
    put("expires_in", expires_in)
    put("refresh_token", refresh_token)
    put("scope", scope)
    put("token_type", token_type)
    toMap()
}