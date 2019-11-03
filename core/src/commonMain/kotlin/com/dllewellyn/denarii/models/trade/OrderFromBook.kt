package com.dllewellyn.denarii.models.currency

import com.ionspin.kotlin.bignum.decimal.BigDecimal

data class OrderFromBook(val price : BigDecimal, val size : BigDecimal, val numberOfOrders : Int)