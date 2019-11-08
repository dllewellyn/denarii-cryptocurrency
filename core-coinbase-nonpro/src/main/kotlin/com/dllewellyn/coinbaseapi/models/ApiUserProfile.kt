package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val avatar_url: String,
    val id: String,
    val name: String? = null,
    val profile_bio: String? = null,
    val profile_location: String? = null,
    val profile_url: String? = null,
    val resource: String,
    val resource_path: String,
    val username: String? = null
)