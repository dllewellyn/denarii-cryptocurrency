package com.dllewellyn.coinbaseapi.adapter

import com.dllewellyn.coinbaseapi.RetrofitCoroutinesBuilder
import com.dllewellyn.coinbaseapi.extensions.unwrap
import com.dllewellyn.coinbaseapi.interfaces.CurrencyPrice
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.OrderBookLevel
import com.dllewellyn.coinbaseapi.models.currency.OrderBookList
import com.dllewellyn.coinbaseapi.models.currency.OrderFromBook
import com.dllewellyn.coinbaseapi.models.trade.CurrencyBuyAndSell
import java.math.BigDecimal


class CurrencyPriceAdapter(private val retrofit: RetrofitCoroutinesBuilder) : CurrencyPrice {

    override suspend fun getCurrencyBuyAndSellPrice(pair: CurrencyPair): CurrencyBuyAndSell {
        return retrofit.getProApi()
            .getTradeTick(pairToString(pair))
            .unwrap()
            .let {
                CurrencyBuyAndSell(
                    pair.baseCurrency,
                    pair.quoteCurrency,
                    BigDecimal(it.ask ?: "0"),
                    BigDecimal(it.bid ?: "0")
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
                            BigDecimal(ask[0] as String),
                            BigDecimal(ask[1] as String),
                            (ask[2] as Double).toInt()
                        )
                    }
                }

                val bids = orderBook.bids.map { bids ->
                    (bids as List<*>).let { ask ->
                        OrderFromBook(
                            BigDecimal(ask[0] as String),
                            BigDecimal(ask[1] as String),
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
