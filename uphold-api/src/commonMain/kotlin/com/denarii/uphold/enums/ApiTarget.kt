package com.denarii.uphold.enums

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializable(TargetSerializer::class)
enum class ApiTarget { ORIGIN, DESTINATION }

@Serializer(forClass = ApiTarget::class)
object TargetSerializer {
    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(decoder: Decoder) =
        ApiTarget.valueOf(decoder.decodeString().toUpperCase())


    override fun serialize(encoder: Encoder, obj: ApiTarget) {
        encoder.encodeString(obj.name.toLowerCase())
    }

}

