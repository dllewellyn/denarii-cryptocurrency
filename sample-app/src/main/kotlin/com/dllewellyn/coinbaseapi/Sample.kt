package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.models.Account
import com.dllewellyn.coinbaseapi.retrievers.CompositeRetriever
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        with(CoinbaseApi()) {

            val api = ApikeyCoinbaseApi(System.getenv("COINBASE_KEY") , System.getenv("COINBASE_SECRET"))

            val remote  = CompositeRetriever<Account>().apply {
                retrievers.add(api.coreAccounts())
                println(this.retrieveData())
            }

            //println(exchangeRateRetriever().getExchangeRates(CryptoCurrency.GBP))
            //println(currencyList().getCurrencyList())
            //println(prices().getSpotPrice(CurrencyPair.fromId("BTC-USD")))
        }
    }
}