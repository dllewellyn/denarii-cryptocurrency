package com.dllewellyn.coinbaseapi.models.currency

data class OrderBookList(
    val asks: List<OrderFromBook>,
    val bids: List<OrderFromBook>,
    val sequence: Int
)