package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.CoinbaseProService
import com.dllewellyn.coinbaseapi.models.account.Transaction
import com.dllewellyn.coinbaseapi.repositories.ReadOnlyPostRepository
import com.dllewellyn.coinbaseapi.unwrap

class AccountHistoryAdapter(private val retrofit: CoinbaseProService) :
    ReadOnlyPostRepository<String, List<Transaction>> {

    override suspend fun retrieveData(arg: String) = retrofit.getAccountHistory(arg).unwrap()
        .map {
            Transaction(
                amount = it.amount,
                balance = it.balance,
                type = it.type,
                id = it.id,
                description = it.details.tradeId,
                date = it.createdAt
            )
        }
}