package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.trade.OrderRequest
import com.dllewellyn.coinbaseapi.models.trade.OrderResponse
import io.reactivex.Single

interface Orders {
    fun placeOrder(orders : OrderRequest) : Single<OrderResponse>
}