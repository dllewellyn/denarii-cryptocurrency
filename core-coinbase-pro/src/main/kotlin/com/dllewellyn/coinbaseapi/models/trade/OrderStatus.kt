package com.dllewellyn.coinbaseapi.models.trade

import java.lang.IllegalArgumentException

enum class OrderStatus {
    CANCELLED,
    OPEN,
    PENDING,
    DONE;

    companion object {
        fun fromString(s : String) = when(s.toLowerCase()) {
            "done" -> DONE
            "open" -> OPEN
            "pending" -> PENDING
            "cancelled" -> CANCELLED
            else -> throw IllegalArgumentException("Unexpected string for order status $s")
        }
    }
}