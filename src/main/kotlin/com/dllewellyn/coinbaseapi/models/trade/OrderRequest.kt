package com.dllewellyn.coinbaseapi.models.trade

import com.dllewellyn.coinbaseapi.models.BuyOrSell
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair

class OrderRequest(val buyOrSell: BuyOrSell, val currency: CurrencyPair, val size: Float, val price: Float,
                   val type : LimitOrMarket)