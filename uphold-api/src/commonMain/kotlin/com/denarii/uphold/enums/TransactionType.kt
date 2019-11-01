package com.denarii.uphold.enums

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializable(TransferTypeSerializer::class)
enum class TransactionType {
    DEPOSIT,
    TRANSFER,
    WITHDRAWAL
}

@Serializer(TransactionType::class)
object TransferTypeSerializer {
    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(decoder: Decoder): TransactionType {
        return TransactionType.valueOf(decoder.decodeString().toUpperCase())
    }

    override fun serialize(encoder: Encoder, obj: TransactionType) {
        encoder.encodeString(obj.name.toLowerCase())
    }

}
