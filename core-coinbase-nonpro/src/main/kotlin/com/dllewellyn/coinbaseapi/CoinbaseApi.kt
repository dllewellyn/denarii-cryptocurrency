package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.adapters.*
import com.dllewellyn.coinbaseapi.http.AuthenticatedApiKeyHttpClient
import com.dllewellyn.coinbaseapi.http.AuthenticatedOauthHttpClient
import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
import com.dllewellyn.coinbaseapi.models.OauthProvider
import com.dllewellyn.coinbaseapi.models.account.Transaction
import com.dllewellyn.coinbaseapi.nonpro.interfaces.*
import com.dllewellyn.denarii.repositories.ReadOnlyRepositoryArgument
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

open class CoinbaseApi {

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
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

interface AuthenticatedApiCalls {
    suspend fun accounts(): Accounts
    suspend fun coreAccounts(): CoreAccounts
    suspend fun coreTransactions(): ReadOnlyRepositoryArgument<String, List<Transaction>>
    suspend fun addresses(): Addresses
    suspend fun userProfile(): UserProfile
}

open class BaseAuthenticatedCoinbaseApi(private val client: InternalHttpClient) : AuthenticatedApiCalls {
    override suspend fun addresses(): Addresses = AddressesAdapter(client)
    override suspend fun userProfile() = UserProfileAdapter(client)
    override suspend fun accounts(): Accounts = AccountsAdapter(client)
    override suspend fun coreAccounts(): CoreAccounts =
        AccountsCoreAdapter(accounts(), coreTransactions(), client, PricesAdapter(client))

    override suspend fun coreTransactions() = TransactionsCoreAdapter(client, PricesAdapter(client))
}

class OauthCoinbaseApi(private val oauthProvider: OauthProvider) : CoinbaseApi(), AuthenticatedApiCalls by
BaseAuthenticatedCoinbaseApi(AuthenticatedOauthHttpClient(oauthProvider))

class ApikeyCoinbaseApi(apiKey: String, secretKey: String) : CoinbaseApi(), AuthenticatedApiCalls by
BaseAuthenticatedCoinbaseApi(
    AuthenticatedApiKeyHttpClient(
        apiKey,
        secretKey,
        url
    )
)