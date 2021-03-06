package com.dllewellyn.coinbaseapi.authentcation

import kotlinx.serialization.Serializable

@Serializable
data class OauthModel(
    val grant_type: String = "authorization_code",
    val code: String,
    val client_id: String,
    val client_secret: String,
    val redirect_uri: String
)