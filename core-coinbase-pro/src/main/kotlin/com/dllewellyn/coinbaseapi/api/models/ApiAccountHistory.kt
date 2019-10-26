package com.dllewellyn.coinbaseapi.api.models


import com.google.gson.annotations.SerializedName

data class ApiAccountHistory(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("balance")
    val balance: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("details")
    val details: ApiDetails,
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String
)