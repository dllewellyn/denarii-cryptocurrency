package com.denarii.uphold.enums

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializable(with = CardTypeSerializer::class)
enum class CardType {
    CARD,
    SEPA
}


@Serializer(forClass = CardType::class)
object CardTypeSerializer {
    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(decoder: Decoder): CardType {  // 3
        return CardType.valueOf(decoder.decodeString().toUpperCase())
    }

    override fun serialize(encoder: Encoder, obj: CardType) {  // 4
        encoder.encodeString(obj.name.toLowerCase())
    }
}

