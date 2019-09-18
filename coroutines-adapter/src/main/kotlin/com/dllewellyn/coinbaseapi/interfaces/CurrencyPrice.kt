package com.dllewellyn.coinbaseapi.interfaces

import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.OrderBookLevel
import com.dllewellyn.coinbaseapi.models.currency.OrderBookList
import com.dllewellyn.coinbaseapi.models.trade.CurrencyBuyAndSell

interface CurrencyPrice {
    /**
     * Get the currency buy and sell price.
     *
     * @param pair the pair to use
     *
     * @return the buy and sell price for this pair, correct(ish) as you receive it
     */
    suspend fun getCurrencyBuyAndSellPrice(pair: CurrencyPair) : CurrencyBuyAndSell

    /**
     * Get the product order book
     *
     * @param orderType -    1	Only the best bid and ask
     *                       2	Top 50 bids and asks (aggregated)
     *                       3	Full order book (non aggregated)
     * @param currencyPair - currency pair to use
     *
     * @return the order book, results will depend on the level specified
     */
    suspend fun getProductOrderBook(pair : CurrencyPair, orderType : OrderBookLevel = OrderBookLevel.LEVEL_1) : OrderBookList
}