package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.denarii.models.currency.OpenOrder
import com.dllewellyn.denarii.models.trade.OrderRequest


interface Orders {
    suspend fun placeOrder(orders: OrderRequest): OpenOrder
    suspend fun retrieveOpenOrders(): List<OpenOrder>
    suspend fun deleteOrder(orderId: String)
    suspend fun deleteAll(): List<String>
    suspend fun orderById(order: String): OpenOrder
}