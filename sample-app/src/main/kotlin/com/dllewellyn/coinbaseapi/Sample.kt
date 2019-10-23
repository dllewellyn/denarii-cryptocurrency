package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.models.Account
import com.dllewellyn.coinbaseapi.models.OauthProvider
import com.dllewellyn.coinbaseapi.multiplatform.databases.AccountsDb
import com.dllewellyn.coinbaseapi.retrievers.CachingRepository
import com.dllewellyn.coinbaseapi.retrievers.CompositeRetriever
import kotlinx.coroutines.runBlocking
import java.io.Console
import java.io.File

fun main() {
    runBlocking {
        with(CoinbaseApi()) {

            val api = OauthCoinbaseApi(OauthProvider(
            ))

            val remote = CompositeRetriever<Account>().apply {
                retrievers.add(api.coreAccounts())
            }

            val local = AccountsDb()

            val cachingRepository = CachingRepository(remote, local, local)
            cachingRepository.initialise()
            cachingRepository.refresh()

            println(cachingRepository.retrieveData())
        }
    }
}
