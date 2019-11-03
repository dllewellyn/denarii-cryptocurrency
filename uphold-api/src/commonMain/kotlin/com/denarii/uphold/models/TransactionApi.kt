package com.denarii.uphold.models


import com.denarii.uphold.enums.TransactionType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionApi(

    @SerialName("application")
    val application: String? = null,

    @SerialName("createdAt")
    val createdAt: String,

    @SerialName("denomination")
    val denomination: Denomination,

    @SerialName("destination")
    val destination: DestinationOrOrigin,

    @SerialName("fees")
    val fees: List<FeeApi>,

    @SerialName("id")
    val id: String,

    @SerialName("message")
    val message: String? = null,

    @SerialName("network")
    val network: String,

    @SerialName("normalized")
    val normalized: List<NormalizedX>,

    @SerialName("origin")
    val origin: DestinationOrOrigin,

    @SerialName("params")
    val params: Params,

    @SerialName("priority")
    val priority: String,

    @SerialName("reference")
    val reference: String? = null,

    @SerialName("status")
    val status: String,

    @SerialName("type")
    val type: TransactionType
)