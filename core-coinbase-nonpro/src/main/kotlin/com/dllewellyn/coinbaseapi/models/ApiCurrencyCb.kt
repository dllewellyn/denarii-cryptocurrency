package com.dllewellyn.coinbaseapi.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class ApiCurrencyCb(
    @SerialName("address_regex")
    val addressRegex: String?=null,
    @SerialName("asset_id")
    val assetId: String?=null,
    @SerialName("code")
    val code: String,
    @SerialName("color")
    val color: String,
    @SerialName("destination_tag_name")
    val destinationTagName: String?=null,
    @SerialName("destination_tag_regex")
    val destinationTagRegex: String?=null,
    @SerialName("exponent")
    val exponent: Int?=null,
    @SerialName("name")
    val name: String,
    @SerialName("sort_index")
    val sortIndex: Int,
    @SerialName("type")
    val type: String
)