package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.Orders
import com.dllewellyn.coinbaseapi.models.trade.OrderRequest
import com.dllewellyn.coinbaseapi.retrofit.CoinbaseProService
import com.dllewellyn.coinbaseapi.retrofit.models.toApi

class OrdersAdapter(private val retrofit: CoinbaseProService) : Orders {

    override fun placeOrder(orders: OrderRequest) = retrofit.placeNewOrder(orders.toApi())
        .map { it.toCore() }

}
