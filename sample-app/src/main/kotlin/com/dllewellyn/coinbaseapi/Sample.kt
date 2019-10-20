package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.models.Account
import com.dllewellyn.coinbaseapi.multiplatform.databases.AccountsDb
import com.dllewellyn.coinbaseapi.retrievers.CachingRepository
import com.dllewellyn.coinbaseapi.retrievers.CompositeRetriever
import kotlinx.coroutines.runBlocking
import java.io.Console
import java.io.File

fun main() {
    runBlocking {
        with(CoinbaseApi()) {

            val api = ApikeyCoinbaseApi(System.getenv("COINBASE_KEY"), System.getenv("COINBASE_SECRET"))

            val remote = CompositeRetriever<Account>().apply {
                retrievers.add(api.coreAccounts())
            }

            val local = AccountsDb()

            val cachingRepository = CachingRepository(remote, local, local)
            cachingRepository.initialise()
            cachingRepository.refresh()

            println(cachingRepository.retrieveData())

            //println(exchangeRateRetriever().getExchangeRates(CryptoCurrency.GBP))
            //println(currencyList().getCurrencyList())
            //println(prices().getSpotPrice(CurrencyPair.fromId("BTC-USD")))
        }
    }
}