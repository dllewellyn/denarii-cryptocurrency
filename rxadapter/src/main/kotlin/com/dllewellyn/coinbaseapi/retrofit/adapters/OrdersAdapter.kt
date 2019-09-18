package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.api.models.toApi
import com.dllewellyn.coinbaseapi.interfaces.Orders
import com.dllewellyn.coinbaseapi.models.trade.OrderRequest
import com.dllewellyn.coinbaseapi.retrofit.services.CoinbaseProService

class OrdersAdapter(private val retrofit: CoinbaseProService) : Orders {
    override fun orderById(order: String) = retrofit.getOrder(order)
        .map { it.toCore() }

    override fun retrieveOpenOrders() = retrofit.getOpenOrders()
        .map { it.map { x -> x.toCore() } }

    override fun placeOrder(orders: OrderRequest) = retrofit.placeNewOrder(orders.toApi())
        .map { it.toCore() }

    override fun deleteOrder(orderId: String) = retrofit.deleteOrder(orderId)

    override fun deleteAll() = retrofit.deleteAll()

}
