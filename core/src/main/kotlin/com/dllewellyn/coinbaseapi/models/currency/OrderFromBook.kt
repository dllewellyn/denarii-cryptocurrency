package com.dllewellyn.coinbaseapi.models.currency

import java.math.BigDecimal

data class OrderFromBook(val price : BigDecimal, val size : BigDecimal, val numberOfOrders : Int)