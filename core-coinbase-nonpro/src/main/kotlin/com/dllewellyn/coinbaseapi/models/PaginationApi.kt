package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable


@Serializable
data class PaginationApi(
    val ending_before: String? = null,
    val limit: Int,
    val next_uri: String? = null,
    val order: String? = null,
    val previous_uri: String? = null,
    val starting_after: String? = null,
    val previous_ending_before: String? = null,
    val next_starting_after : String? = null
)