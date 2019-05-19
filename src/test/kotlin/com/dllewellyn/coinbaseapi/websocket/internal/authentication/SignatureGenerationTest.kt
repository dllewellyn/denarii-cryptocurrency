package com.dllewellyn.coinbaseapi.websocket.internal.authentication

import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.*
import kotlin.random.Random


class SignatureGenerationTest {

    @Test
    fun `test that signature genertor produces correct response`() {
        val secretKey = Base64.getEncoder().encodeToString(Random.Default.nextBytes(64))

        val timestamp = 5
        val method = "GET"
        val body = null
        val url = "http://coinbase.pro.api.com/accouunts"

        assertNotNull(SignatureGeneration(timestamp.toLong(), method, url, body, secretKey).generate())
    }

}