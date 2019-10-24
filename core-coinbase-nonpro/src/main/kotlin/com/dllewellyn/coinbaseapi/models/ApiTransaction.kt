package com.dllewellyn.coinbaseapi.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiTransaction(
    val apiAmount: ApiAmount,
    val apiBuy: ApiBuy,
    val created_at: String,
    val description: String?=null,
    val details: ApiDetails,
    val id: String,
    val instant_exchange: Boolean,
    val native_amount: ApiNativeAmount,
    val resource: String,
    val resource_path: String,
    val status: String,
    val type: String,
    val updated_at: String
)