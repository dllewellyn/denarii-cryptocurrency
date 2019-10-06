package com.dllewellyn.coinbaseapi.models


import kotlinx.serialization.Serializable

@Serializable
data class CurrencyApi(
    val address_regex: String?=null,
    val asset_id: String?=null,
    val code: String,
    val color: String,
    val destination_tag_name: String?=null,
    val destination_tag_regex: String?=null,
    val exponent: Int,
    val name: String,
    val sort_index: Int,
    val type: String
)