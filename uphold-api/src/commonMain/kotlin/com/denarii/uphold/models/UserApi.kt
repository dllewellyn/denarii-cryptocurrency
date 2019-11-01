package com.denarii.uphold.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApi(

    @SerialName("address")
    val address: UserAddress,

    @SerialName("balances")
    val balances: Balances,

    @SerialName("birthdate")
    val birthdate: String,

    @SerialName("country")
    val country: String,

    @SerialName("currencies")
    val currencies: List<String>,

    @SerialName("email")
    val email: String,

    @SerialName("firstName")
    val firstName: String,

    @SerialName("id")
    val id: String,

    @SerialName("lastName")
    val lastName: String,

    @SerialName("memberAt")
    val memberAt: String,

    @SerialName("name")
    val name: String,

    @SerialName("phones")
    val phones: List<Phone>,

    @SerialName("settings")
    val settings: SettingsX,

    @SerialName("state")
    val state: String,

    @SerialName("status")
    val status: String,

    @SerialName("type")
    val type: String,

    @SerialName("verifications")
    val verifications: Verifications
)