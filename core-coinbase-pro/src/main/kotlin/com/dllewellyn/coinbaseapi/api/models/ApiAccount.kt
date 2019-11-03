package com.dllewellyn.coinbaseapi.api.models

import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.google.gson.annotations.SerializedName
import com.ionspin.kotlin.bignum.decimal.BigDecimal

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
        BigDecimal.parseString(balance, 10),
        BigDecimal.parseString(available, 10),
        BigDecimal.parseString(hold, 10),
        id,
        "coinbase-pro"
    )
}