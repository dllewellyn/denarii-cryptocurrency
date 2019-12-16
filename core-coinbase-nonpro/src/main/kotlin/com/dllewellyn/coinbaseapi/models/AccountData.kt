package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.enums.AccountType
import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountData(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: AccountType,
    @SerialName("primary")
    val primary: Boolean,
    @SerialName("balance")
    val balance: BalanceApi,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    @SerialName("resource")
    val resource: String,
    @SerialName("resource_path")
    val resource_path: String,
    @SerialName("currency")
    val currency: ApiCurrencyCb
)



fun AccountData.toCore() = Account(
    SupportedCurrency(balance.currency),
    BigDecimal.parseString(balance.amount, 10),
    null,
    null,
    id,
    "coinbase"
)