package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.CoinbaseProService
import com.dllewellyn.coinbaseapi.api.models.toApi
import com.dllewellyn.coinbaseapi.interfaces.Orders
import com.dllewellyn.coinbaseapi.unwrap
import com.dllewellyn.denarii.models.trade.OrderRequest

class OrdersAdapter(private val retrofit: CoinbaseProService) : Orders {
    override suspend fun placeOrder(orders: OrderRequest) = retrofit.placeNewOrder(orders.toApi()).unwrap().toCore()
    override suspend fun retrieveOpenOrders() = retrofit.getOpenOrders().unwrap().map { it.toCore() }
    override suspend fun deleteOrder(orderId: String) = retrofit.deleteOrder(orderId).unwrap()
    override suspend fun deleteAll() = retrofit.deleteAll().unwrap()
    override suspend fun orderById(order: String) = retrofit.getOrder(order).unwrap().toCore()
}