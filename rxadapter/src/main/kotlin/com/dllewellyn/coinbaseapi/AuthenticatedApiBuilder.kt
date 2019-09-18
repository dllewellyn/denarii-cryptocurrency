package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.exceptions.InvalidConfigurationException
import com.dllewellyn.coinbaseapi.interfaces.Accounts
import com.dllewellyn.coinbaseapi.interfaces.Orders
import com.dllewellyn.coinbaseapi.retrofit.RetrofitRxApiBuilder
import com.dllewellyn.coinbaseapi.retrofit.adapters.AccountsAdapter
import com.dllewellyn.coinbaseapi.retrofit.adapters.OrdersAdapter
import com.dllewellyn.coinbaseapi.retrofit.services.CoinbaseProService
import com.dllewellyn.coinbaseapi.websocket.internal.adapters.WebSocketAdapter

interface AuthenticatedApi {
    fun accounts() : Accounts
    fun orders() : Orders
}

class AuthenticatedApiBuilder {
    var secretKey: String? = null
    var apiKey: String? = null
    var password: String? = null
    var sandbox = false

    @Throws(InvalidConfigurationException::class)
    fun build() =
        AuthenticatedApiImpl(
            nullOrException(password, ::throwException),
            nullOrException(apiKey, ::throwException),
            nullOrException(secretKey, ::throwException),
            sandbox
        )

    private fun throwException() = InvalidConfigurationException()

    private fun <T>nullOrException(any : T?, exception : () -> Throwable) : T {
        return any ?: throw exception()
    }

}

fun authenticated_builder(block: AuthenticatedApiBuilder.() -> Unit) = AuthenticatedApiBuilder().apply {
        block()
    }


class AuthenticatedApiImpl(
    private val password: String,
    private val apiKey: String,
    private val secretKey: String,
    private val sandbox : Boolean
) : AuthenticatedApi {

    private val retrofit: CoinbaseProService by lazy {
        RetrofitRxApiBuilder(sandbox)
            .getProApiAuthentication(password, apiKey, secretKey)
    }

    override fun orders() : Orders = OrdersAdapter(retrofit)

    private val webSocketAdapter: WebSocketAdapter by lazy {
        WebSocketAdapter(sandbox)
    }

    override fun accounts() : Accounts = AccountsAdapter(retrofit)


}
