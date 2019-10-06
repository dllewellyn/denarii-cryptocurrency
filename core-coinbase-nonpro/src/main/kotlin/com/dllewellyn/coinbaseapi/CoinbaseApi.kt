package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.adapters.AccountsAdapter
import com.dllewellyn.coinbaseapi.adapters.CurrencyListAdapter
import com.dllewellyn.coinbaseapi.adapters.ExchangeRateRetriverAdapter
import com.dllewellyn.coinbaseapi.adapters.PricesAdapter
import com.dllewellyn.coinbaseapi.http.AuthenticatedApiKeyHttpClient
import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.interfaces.Accounts
import com.dllewellyn.coinbaseapi.interfaces.CurrencyList
import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
import com.dllewellyn.coinbaseapi.interfaces.Prices
import com.dllewellyn.coinbaseapi.models.UserAccountApi
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get

open class CoinbaseApi {

    private val client = HttpClient(CIO) {
        install(JsonFeature)
    }

    companion object {
        const val url = "https://api.coinbase.com/v2"
    }

    private val httpClient = InternalHttpClient(client, url)

    /**
     * Get current exchange rates. Default base currency is USD but it can be defined as any supported currency. Returned rates will define the exchange rate for one unit of the base currency.
     */
    fun exchangeRateRetriever(): ExchangeRateRetriver =
        ExchangeRateRetriverAdapter(httpClient)

    /**
     * List known currencies. Currency codes will conform to the ISO 4217 standard where possible. Currencies which have or had no representation in ISO 4217 may use a custom code (e.g. BTC).
     */
    fun currencyList(): CurrencyList =
        CurrencyListAdapter(httpClient)

    /** Get the total price to buy one bitcoin or ether.

    Note that exchange rates fluctuates so the price is only correct for seconds at the time. This buy price includes standard Coinbase fee (1%) but excludes any other fees including bank fees
     */
    fun prices(): Prices = PricesAdapter(httpClient)

}

class ApikeyCoinbaseApi(apiKey: String, secretKey: String) : CoinbaseApi() {
    private val authenticatedApiHttpClient = AuthenticatedApiKeyHttpClient(
        apiKey,
        secretKey,
        url
    )

    suspend fun accounts() : Accounts =
        AccountsAdapter(authenticatedApiHttpClient)

}