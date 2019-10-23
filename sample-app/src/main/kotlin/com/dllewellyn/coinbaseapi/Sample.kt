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
                "048a8aaff8e35f175ac53987263a54dead404c23ddbfbc5f78b10e064a9a02b0",
                1571855983,
                7200,
                "af3570637c3d1466581c587cab85deb000735ca4ecb2072d13d9206c8e1b248b",
                "wallet:user:read",
                "bearer"
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