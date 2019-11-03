package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Transactions(

    @SerialName("send")
    val send: Send,

    @SerialName("transfer")
    val transfer: Transfer,

    @SerialName("withdraw")
    val withdraw: Withdraw
)