package com.denarii.uphold.models


import com.denarii.uphold.enums.CardType
import com.denarii.uphold.enums.CardTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Node(

    @SerialName("id")
    val id: String,

    @SerialName("type")
    @Serializable(CardTypeSerializer::class)
    val type: CardType,

    @SerialName("user")
    val user: User
)