package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.exceptions.InvalidConfigurationException
import org.junit.Test

class AuthenticatedApiBuilderTest {

    @Test(expected = InvalidConfigurationException::class)
    fun `test that if we dont pass an essential piece of information we get an error in response`() {
        authenticated_builder {
            apiKey = "key"
            password = "password"
            // Missing secret key
        }.build()
    }

    @Test
    fun `test that if we pass all essential piece of information we get no error in response`() {
        authenticated_builder {
            apiKey = "key"
            password = "password"
            secretKey = "blahblah=="
        }.build()
    }
}