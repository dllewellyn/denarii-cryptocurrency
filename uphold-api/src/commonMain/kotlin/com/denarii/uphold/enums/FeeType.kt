package com.denarii.uphold.enums

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializable(FeeTypeSerializer::class)
enum class FeeType {
    DEPOSIT,
    EXCHANGE,
    NETWORK,
    WITHDRAWAL
}

@Serializer(forClass = FeeType::class)
object FeeTypeSerializer {
    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(decoder: Decoder) =
        FeeType.valueOf(decoder.decodeString().toUpperCase())

    override fun serialize(encoder: Encoder, obj: FeeType) {
        encoder.encodeString(obj.name.toLowerCase())
    }

}
