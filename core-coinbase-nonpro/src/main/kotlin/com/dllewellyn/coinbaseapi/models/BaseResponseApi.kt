package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseApi<T>(
    val `data`: List<T>, val pagination: PaginationApi?,
    val warnings: List<WarningApi>? = null
)


@Serializable
data class BaseResponseApiSingle<T>(
    val `data`: T,
    val warnings: List<WarningApi>? = null
)