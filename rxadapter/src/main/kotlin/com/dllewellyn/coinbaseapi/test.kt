package com.dllewellyn.coinbaseapi

import com.dllewellyn.coinbaseapi.models.*
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.CurrencyValue
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.ConcurrentHashMap


class LiveOrderBookIml : LiveOrderBook {
    override fun getMonitoredCurrencies() = buys.keys().toList()
    override fun getBuyPriceForPair(pair: CurrencyPair) =
        buys[pair]?.let {
            it.sortByDescending { amount -> amount.buyAndSell.amount }
            it.first()
        }

    override fun getSellPriceForPair(pair: CurrencyPair) = sell[pair]?.let {
        it.sortBy { amount -> amount.buyAndSell.amount }
        it.first()
    }

    val buys = ConcurrentHashMap<CurrencyPair, MutableList<EventResponse.Level2Update>>()
    val sell = ConcurrentHashMap<CurrencyPair, MutableList<EventResponse.Level2Update>>()

    fun run() {
        RetrofitApi.sandbox = true
        RetrofitApi.subscription().subscribeToEvent(
            Channel.Type2().only(),
            CurrencyPair.fromId("BTC-USD"),
            CurrencyPair.fromId("ETH-BTC")
        )
            .doAfterNext {
                if (it is EventResponse.Level2Snapshot) {
                    val pair = CurrencyPair.fromId(
                        "${it.buyAndSell.currencyFrom}-${it.buyAndSell.currencyTo}"
                    )
                    buys[pair] = EventResponse.Level2Update(
                        buyAndSell = CurrencyValue(
                            it.buyAndSell.currencyFrom,
                            it.buyAndSell.currencyTo,
                            it.buyAndSell.buy
                        ),
                        buyOrSell = BuyOrSell.BUY,
                        size = 0.0
                    ).onlyMutable()


                    sell[pair] = EventResponse.Level2Update(
                        buyAndSell = CurrencyValue(
                            it.buyAndSell.currencyFrom,
                            it.buyAndSell.currencyTo,
                            it.buyAndSell.sell
                        ),
                        buyOrSell = BuyOrSell.SELL,
                        size = 0.0
                    ).onlyMutable()

                }

                if (it is EventResponse.Level2Update) {
                    val pair = CurrencyPair.fromId(
                        "${it.buyAndSell.currencyFrom}-${it.buyAndSell.currencyTo}"
                    )

                    if (it.buyOrSell == BuyOrSell.BUY) {
                        if (it.size == 0.0) {
                            buys[pair]?.filter { amnt -> it.buyAndSell.amount == amnt.buyAndSell.amount }
                                ?.forEach { a -> buys[pair]?.remove(a) }
                        } else {
                            buys[pair]?.add(it)
                        }
                    } else {
                        if (it.size == 0.0) {
                            sell[pair]?.filter { amnt -> it.buyAndSell.amount == amnt.buyAndSell.amount }
                                ?.forEach { a -> sell[pair]?.remove(a) }
                        } else {
                            sell[pair]?.add(it)
                        }
                    }
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .onExceptionResumeNext { }
            .subscribe()
    }
}

fun main() {
    with (LiveOrderBookIml()) {
        run()

        if (buys.isNotEmpty() && sell.isNotEmpty()) {
            println("***")
            buys.forEach {
                it.value.sortByDescending { amount -> amount.buyAndSell.amount }
                println(it.value.first())
            }

            sell.forEach {
                it.value.sortBy { amount -> amount.buyAndSell.amount }
                println(it.value.first())
            }

            buys.forEach {
                println(
                    "Spread: ${(sell[it.key]?.first()?.buyAndSell?.amount?.minus(buys[it.key]?.first()?.buyAndSell?.amount!!))}"
                )
            }
        }
    }
}
