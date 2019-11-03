package com.dllewellyn.coinbaseapi.models.account

import kotlinx.serialization.Serializable

/**
 * Amount: Amount for the transaction
 * Balance: Optional: Balance after transaction
 * Description: A description of the transaction
 * Id : A unique ID for the transaction
 * Status: Optional: Status of the transaction
 * Type: String, can be anything. E.g. buy (etc)
 * Date: Any date we can use for the transaction
 */
@Serializable
data class Transaction(
    val amount: String,
    val balance: String? = null,
    val description: String? = null,
    val id: String,
    val status: String? = null,
    val type: String,
    val date: String,
    val nativeCurrency: String? = null,
    val nativeAmount: String? = null
)