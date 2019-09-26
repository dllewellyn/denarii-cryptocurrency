package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.CurrencyPrice
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.OrderBookLevel
import com.dllewellyn.coinbaseapi.models.currency.OrderBookList
import com.dllewellyn.coinbaseapi.models.currency.OrderFromBook
import com.dllewellyn.coinbaseapi.models.trade.CurrencyBuyAndSell
import com.dllewellyn.coinbaseapi.retrofit.RetrofitRxApiBuilder
import io.reactivex.Single
import java.math.BigDecimal

class CurrencyPriceAdapter(private val retrofitApiBuilder: RetrofitRxApiBuilder) : CurrencyPrice {

    /**
     * The ask price is what sellers are willing to take for it. If you are selling a stock, you are going to get the bid price,
     * if you are buying a stock you are going to get the ask price.
     */
    override fun getCurrencyBuyAndSellPrice(pair: CurrencyPair): Single<CurrencyBuyAndSell> {
        return retrofitApiBuilder.getProApi()
            .getTradeTick(pairToString(pair))
            .map {
                CurrencyBuyAndSell(
                    pair.baseCurrency,
                    pair.quoteCurrency,
                    BigDecimal(it.ask ?: "0"),
                    BigDecimal(it.bid ?: "0")
                )
            }
    }

    override fun getProductOrderBook(pair: CurrencyPair, orderType: OrderBookLevel): Single<OrderBookList> {
        return retrofitApiBuilder.getProApi()
            .getOrderbook(pairToString(pair), orderType.level)
            .map { orderBook ->
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
