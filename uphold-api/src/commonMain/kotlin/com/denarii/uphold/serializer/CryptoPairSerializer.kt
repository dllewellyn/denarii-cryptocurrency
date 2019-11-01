package com.denarii.uphold.serializer

import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor

@Serializer(forClass = CurrencyPair::class)
object CryptoPairSerializer {
    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(decoder: Decoder): CurrencyPair {
        val data = decoder.decodeString()
        return CurrencyPair.fromId("${data.substring(0, 3)}-${data.substring(3, 6)}")
    }

    override fun serialize(encoder: Encoder, obj: CurrencyPair) {
        with(obj.id.split("-")) {
            encoder.encodeString("${this[0]}${this[1]}")
        }
    }
}