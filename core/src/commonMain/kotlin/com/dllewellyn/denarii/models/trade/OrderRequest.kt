package com.dllewellyn.denarii.models.trade

import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.marketinfo.BuyOrSell
import com.ionspin.kotlin.bignum.decimal.BigDecimal

class OrderRequest(val buyOrSell: BuyOrSell, val currency: CurrencyPair, val size: BigDecimal, val price: BigDecimal,
                   val type : LimitOrMarket)