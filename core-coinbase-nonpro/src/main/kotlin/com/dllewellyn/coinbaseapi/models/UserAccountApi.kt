package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable


@Serializable
data class UserAccountApi(
    val `data`: List<AccountData>,
    val pagination: PaginationApi?
)