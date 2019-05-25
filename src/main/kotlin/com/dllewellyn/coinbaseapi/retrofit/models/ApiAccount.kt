package com.dllewellyn.coinbaseapi.retrofit.models

import com.dllewellyn.coinbaseapi.models.Account
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import com.google.gson.annotations.SerializedName

data class ApiAccount(
    @SerializedName("available") val available: String,
    @SerializedName("balance") val balance: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("hold") val hold: String,
    @SerializedName("id") val id: String,
    @SerializedName("profile_id") val profile_id: String
) {
    fun toCore() = Account(
        SupportedCurrency(currency, currency, 0.0),
        balance.toDouble(),
        available.toDouble(),
        hold.toDouble(),
        id
    )
}