package com.denarii.uphold.enums

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor


@Serializable(with = StatusSerializer::class)
enum class Status(val status: String) {
    OK("ok"),
    FAILED("failed")
}

@Serializer(forClass = Status::class)
object StatusSerializer {
    override val descriptor: SerialDescriptor
        get() = StringDescriptor   // 2

    override fun deserialize(decoder: Decoder): Status {  // 3
        return Status.valueOf(decoder.decodeString().toUpperCase())
    }

    override fun serialize(encoder: Encoder, obj: Status) {  // 4
        encoder.encodeString(obj.name.toLowerCase())
    }
}