package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.RetrofitCoroutinesBuilder
import com.dllewellyn.coinbaseapi.interfaces.CurrencyPrice
import com.dllewellyn.coinbaseapi.unwrap
import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.currency.OrderBookList
import com.dllewellyn.denarii.models.currency.OrderFromBook
import com.dllewellyn.denarii.models.trade.CurrencyBuyAndSell
import com.dllewellyn.denarii.models.trade.OrderBookLevel
import com.ionspin.kotlin.bignum.decimal.BigDecimal


class CurrencyPriceAdapter(private val retrofit: RetrofitCoroutinesBuilder) : CurrencyPrice {

    override suspend fun getCurrencyBuyAndSellPrice(pair: CurrencyPair): CurrencyBuyAndSell {
        return retrofit.getProApi()
            .getTradeTick(pairToString(pair))
            .unwrap()
            .let {
                CurrencyBuyAndSell(
                    pair.baseCurrency,
                    pair.quoteCurrency,
                    BigDecimal.parseString(it.ask ?: "0", 10),
                    BigDecimal.parseString(it.bid ?: "0", 10)
                )
            }
    }

    override suspend fun getProductOrderBook(pair: CurrencyPair, orderType: OrderBookLevel): OrderBookList {
        return retrofit.getProApi()
            .getOrderbook(pairToString(pair), orderType.level)
            .unwrap()
            .let { orderBook ->
                val asks = orderBook.asks.map { asks ->
                    (asks as List<*>).let { ask ->
                        OrderFromBook(
                            BigDecimal.parseString(ask[0] as String, 10),
                            BigDecimal.parseString(ask[1] as String, 10),
                            (ask[2] as Double).toInt()
                        )
                    }
                }

                val bids = orderBook.bids.map { bids ->
                    (bids as List<*>).let { ask ->
                        OrderFromBook(
                            BigDecimal.parseString(ask[0] as String, 10),
                            BigDecimal.parseString(ask[1] as String, 10),
                            (ask[2] as Double).toInt()
                        )
                    }
                }

                OrderBookList(asks, bids, orderBook.sequence.toInt())

            }
    }

    private fun pairToString(pair: CurrencyPair): String {
        return "${pair.baseCurrency}-${pair.quoteCurrency}"
    }
}
