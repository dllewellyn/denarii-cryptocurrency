package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Otp(

    @SerialName("login")
    val login: Login,

    @SerialName("transactions")
    val transactions: Transactions,

    @SerialName("vmc")
    val vmc: Vmc
)