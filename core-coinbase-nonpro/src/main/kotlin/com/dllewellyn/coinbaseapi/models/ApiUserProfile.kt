package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiUserProfile(
    val `data`: UserData
)

@Serializable
data class UserData(
    val avatar_url: String,
    val id: String,
    val name: String,
    val profile_bio: String,
    val profile_location: String,
    val profile_url: String,
    val resource: String,
    val resource_path: String,
    val username: String
)