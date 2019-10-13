package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.retrievers.CompositeRetriever
import com.dllewellyn.coinbaseapi.models.Account
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        with(CoinbaseApi()) {

            val api = ApikeyCoinbaseApi(
                "P4gFDGAz106UOcd8",
                "zVnmkhCfRsYNs4UVWwyS3SCM6kN0luFl"
            )

            with(CompositeRetriever<Account>()) {
                retrievers.add(api.coreAccounts())
                println(retrieveData())
            }

            //println(exchangeRateRetriever().getExchangeRates(CryptoCurrency.GBP))
            //println(currencyList().getCurrencyList())
            //println(prices().getSpotPrice(CurrencyPair.fromId("BTC-USD")))
        }
    }
}