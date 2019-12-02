package com.dllewellyn.coinbaseapi.enums

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializable(AccountTypeSerializer::class)
enum class AccountType {
    FIAT,
    WALLET,
    VAULT
}

@Serializer(forClass = AccountType::class)
class AccountTypeSerializer() {

    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(decoder: Decoder): AccountType {
        return AccountType.valueOf(decoder.decodeString().toUpperCase())
    }

    override fun serialize(encoder: Encoder, obj: AccountType) {
        encoder.encodeString(obj.name.toLowerCase())
    }
}