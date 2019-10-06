package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class AccountData(
    val balance: BalanceApi,
    val created_at: String,
    val currency: String,
    val id: String,
    val name: String,
    val primary: Boolean,
    val ready: Boolean?=null,
    val resource: String,
    val resource_path: String,
    val type: String,
    val updated_at: String?=null
)