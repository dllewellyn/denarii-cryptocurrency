package com.dllewellyn.coinbaseapi.retrofit.models

import com.google.gson.annotations.SerializedName

data class ApiAccount(
    @SerializedName("available") val available: String,
    @SerializedName("balance") val balance: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("hold") val hold: String,
    @SerializedName("id") val id: String,
    @SerializedName("profile_id") val profile_id: String
)