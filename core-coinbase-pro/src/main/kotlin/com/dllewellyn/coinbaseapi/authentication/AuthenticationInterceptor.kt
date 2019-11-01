package com.dllewellyn.coinbaseapi.authentication

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
        val hasher = Mac.getInstance("HmacSHA256")
        hasher.init(
            SecretKeySpec(
                Base64.getDecoder().decode(secretkey), "HmacSHA256"
            )
        )

        val builder = StringBuilder()
        builder.append(timestmap)
        builder.append(method.toUpperCase())
        builder.append(url)

        body?.let {
            if (it != "null") {
                builder.append(it)
            }
        }

        return Base64.getEncoder().encodeToString(hasher.doFinal(builder.toString().toByteArray()))
    }
}
