package com.dllewellyn.denarii.serializer

import com.dllewellyn.denarii.models.currency.SupportedCurrency
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor

@Serializer(forClass = SupportedCurrency::class)
object SupportedCurrencySerializer {
    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(decoder: Decoder): SupportedCurrency {
        return SupportedCurrency(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, obj: SupportedCurrency) {
        encoder.encodeString(obj.id)
    }
}