package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.CurrencyPrice
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.OrderBookLevel
import com.dllewellyn.coinbaseapi.models.currency.OrderBookList
import com.dllewellyn.coinbaseapi.models.currency.OrderFromBook
import com.dllewellyn.coinbaseapi.models.trade.CurrencyBuyAndSell
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder
import io.reactivex.Single

class CurrencyPriceAdapter(private val retrofitApiBuilder: RetrofitApiBuilder) : CurrencyPrice {

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
                    it.ask?.toDouble() ?: 0.0,
                    it.bid?.toDouble() ?: 0.0
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
                            (ask[0] as String).toDouble(),
                            (ask[1] as String).toDouble(),
                            (ask[2] as Double).toInt()
                        )
                    }
                }

                val bids = orderBook.bids.map { bids ->
                    (bids as List<*>).let { ask ->
                        OrderFromBook(
                            (ask[0] as String).toDouble(),
                            (ask[1] as String).toDouble(),
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
