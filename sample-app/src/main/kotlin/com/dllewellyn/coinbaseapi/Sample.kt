package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.api.models.ApiKeyAuth
import com.dllewellyn.coinbaseapi.math.currentValue
import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.multiplatform.databases.AccountsDb
import com.dllewellyn.coinbaseapi.retrievers.CachingRepository
import com.dllewellyn.coinbaseapi.retrievers.CompositeRetriever
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        with(CoinbaseApi()) {


            val api = ApikeyCoinbaseApi(System.getenv("COINBASE_KEY"), System.getenv("COINBASE_SECRET"))

            val cbApi = CoinbaseProAuthenticatedApiImpl(
                ApiKeyAuth(
                    System.getenv("COINBASE_PRO_SECRET"),
                    System.getenv("COINBASE_PRO_KEY"),
                    System.getenv("COINBASE_PRO_PASSWORD")
                )
            )

            val remote = CompositeRetriever<Account>().apply {
                retrievers.add(api.coreAccounts())
                retrievers.add(cbApi.accounts())
            }

            val local = AccountsDb()

            val cachingRepository = CachingRepository(remote, local, local)
            cachingRepository.initialise()
            cachingRepository.refresh()

            cachingRepository.retrieveData()
                .currentValue(CryptoCurrency.GBP, exchangeRateRetriever())
                .let {
                    println(it.toPlainString())
                }

        }
    }
}
