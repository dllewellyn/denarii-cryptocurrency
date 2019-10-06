package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseApiResponse(val warnings : List<WarningApi>?=null)