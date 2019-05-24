package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.OpenOrder
import com.dllewellyn.coinbaseapi.models.trade.OrderRequest
import io.reactivex.Completable
import io.reactivex.Single

interface Orders {
    fun placeOrder(orders : OrderRequest) : Single<OpenOrder>
    fun retrieveOpenOrders() : Single<List<OpenOrder>>
    fun deleteOrder(orderId : String) : Completable
    fun deleteAll() : Single<List<String>>
}