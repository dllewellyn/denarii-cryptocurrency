package com.dllewellyn.coinbaseapi.models

import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.Serializable

@Serializable
data class AccountData(
    val balance: BalanceApi,
    val created_at: String? = null,
    val currency: CurrencyApi,
    val id: String,
    val name: String,
    val primary: Boolean,
    val allow_deposits: Boolean,
    val allow_withdrawals: Boolean,
    val ready: Boolean? = null,
    val resource: String,
    val resource_path: String,
    val type: String,
    val updated_at: String? = null
)

fun AccountData.toCore() = Account(
    SupportedCurrency(balance.currency),
    BigDecimal.parseString(balance.amount, 10),
    null,
    null,
    id,
    "coinbase"
)