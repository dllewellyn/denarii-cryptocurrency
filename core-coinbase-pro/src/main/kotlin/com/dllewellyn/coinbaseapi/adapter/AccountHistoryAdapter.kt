package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.CoinbaseProService
import com.dllewellyn.coinbaseapi.api.models.ApiAccountHistory
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import com.dllewellyn.coinbaseapi.unwrap

class AccountHistoryAdapter(private val retrofit: CoinbaseProService) :
    ReadOnlyPostRepository<String, List<ApiAccountHistory>> {

    override suspend fun retrieveData(arg: String) = retrofit.getAccountHistory(arg).unwrap()
}