package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class ContactApi(

    @SerialName("addresses")
    val addresses: List<String>,

    @SerialName("company")
    val company: String,

    @SerialName("emails")
    val emails: List<String>,

    @SerialName("firstName")
    val firstName: String,

    @SerialName("id")
    val id: String,

    @SerialName("lastName")
    val lastName: String,

    @SerialName("name")
    val name: String
)