package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.api.models.ApiKeyAuth
import com.dllewellyn.coinbaseapi.models.account.Account
import com.dllewellyn.denarii.base.retrieveDatabase
import kotlinx.coroutines.runBlocking
import com.dllewellyn.denarii.base.databases.AccountsDb
import com.dllewellyn.denarii.retrievers.CachingRepository
import com.dllewellyn.denarii.retrievers.CompositeRetriever

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

            val local = AccountsDb(retrieveDatabase())

            val cachingRepository = CachingRepository(remote, local, local)
            cachingRepository.initialise()
         //   cachingRepository.refresh()

            cachingRepository.retrieveData()
                .forEach {
                    println(it)
                }

        }
    }
}
