package com.dllewellyn.coinbaseapi.retrofit.models

import com.google.gson.annotations.SerializedName

data class ApiValueResponse(
    @SerializedName("data") val data : ApiValue
)

data class ApiValue(
    @SerializedName("base") val base : String,
    @SerializedName("amount") val amount : String,
    @SerializedName("currency") val currency : String
)