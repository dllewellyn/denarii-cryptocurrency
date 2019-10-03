package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.OpenOrder
import com.dllewellyn.coinbaseapi.models.trade.OrderRequest


interface Orders {
    suspend fun placeOrder(orders: OrderRequest): OpenOrder
    suspend fun retrieveOpenOrders(): List<OpenOrder>
    suspend fun deleteOrder(orderId: String)
    suspend fun deleteAll(): List<String>
    suspend fun orderById(order: String): OpenOrder
}