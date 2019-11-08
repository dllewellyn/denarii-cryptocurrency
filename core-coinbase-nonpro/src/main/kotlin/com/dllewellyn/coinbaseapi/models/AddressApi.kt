package com.dllewellyn.coinbaseapi.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class AddressApi(
    @SerialName("address")
    val address: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("network")
    val network: String,
    @SerialName("resource")
    val resource: String,
    @SerialName("resource_path")
    val resourcePath: String,
    @SerialName("updated_at")
    val updatedAt: String
)