package com.dllewellyn.coinbaseapi.models.trade

import com.dllewellyn.coinbaseapi.models.BuyOrSell
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import java.math.BigInteger

class OrderRequest(val buyOrSell: BuyOrSell, val currency: CurrencyPair, val size: BigInteger, val price: BigInteger,
                   val type : LimitOrMarket)