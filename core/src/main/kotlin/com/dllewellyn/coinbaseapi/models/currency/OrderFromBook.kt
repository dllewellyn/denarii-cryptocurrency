package com.dllewellyn.coinbaseapi.models.currency

import java.math.BigInteger

data class OrderFromBook(val price : BigInteger, val size : BigInteger, val numberOfOrders : Int)