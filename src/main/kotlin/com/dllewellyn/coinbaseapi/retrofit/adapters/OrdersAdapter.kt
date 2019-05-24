package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.Orders
import com.dllewellyn.coinbaseapi.models.trade.OrderRequest
import com.dllewellyn.coinbaseapi.retrofit.CoinbaseProService
import com.dllewellyn.coinbaseapi.retrofit.models.toApi
import io.reactivex.Completable
import io.reactivex.Single

class OrdersAdapter(private val retrofit: CoinbaseProService) : Orders {

    override fun retrieveOpenOrders() = retrofit.getOpenOrders()
        .map { it.map { x -> x.toCore() } }

    override fun placeOrder(orders: OrderRequest) = retrofit.placeNewOrder(orders.toApi())
        .map { it.toCore() }

    override fun deleteOrder(orderId: String) = retrofit.deleteOrder(orderId)

    override fun deleteAll() = retrofit.deleteAll()

}
