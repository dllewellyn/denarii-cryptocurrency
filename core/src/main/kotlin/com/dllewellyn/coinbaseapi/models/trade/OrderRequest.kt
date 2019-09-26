package com.dllewellyn.coinbaseapi.models.trade

import com.dllewellyn.coinbaseapi.models.BuyOrSell
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import java.math.BigDecimal

class OrderRequest(val buyOrSell: BuyOrSell, val currency: CurrencyPair, val size: BigDecimal, val price: BigDecimal,
                   val type : LimitOrMarket)