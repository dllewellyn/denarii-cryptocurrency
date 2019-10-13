package com.dllewellyn.coinbaseapi.api.models

import com.dllewellyn.coinbaseapi.models.Account
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

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
        BigDecimal(balance),
        BigDecimal(available),
        BigDecimal(hold),
        id,
        "coinbase-pro"
    )
}