package com.dllewellyn.coinbaseapi.authentcation

import kotlinx.serialization.toUtf8Bytes
import java.lang.StringBuilder
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


class SignatureGeneration(
    private val timestmap: Long,
    private val method: String,
    private val url: String,
    private val body: String?,
    private val secretkey: String
) {
    fun generate(): String {
        val builder = StringBuilder()
        builder.append(timestmap)
        builder.append(method.toUpperCase())
        builder.append("/v2/$url")

        body?.let {
            if (it != "null") {
                builder.append(it)
            }
        }

        val encoding = builder.toString()
        return bytesToHex2(HmacGenerator.hmacSHA256(secretkey, encoding))
    }

    private fun bytesToHex2(hashInBytes: ByteArray): String {

        val sb = StringBuilder()
        for (i in hashInBytes.indices) {
            val hex = Integer.toHexString(0xff and hashInBytes[i].toInt())
            if (hex.length == 1) sb.append('0')
            sb.append(hex)
        }
        return sb.toString()

    }
}