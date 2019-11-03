package com.dllewellyn.denarii.serializer

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor


@Serializer(forClass = BigDecimal::class)
object BigDecimalSerializer {
    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(decoder: Decoder): BigDecimal {
        return BigDecimal.parseString(decoder.decodeString(), 10)
    }

    override fun serialize(encoder: Encoder, obj: BigDecimal) {
        encoder.encodeString(obj.toStringExpanded())
    }
}