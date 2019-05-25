package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.CurrencyPrice
import com.dllewellyn.coinbaseapi.models.currency.CurrencyPair
import com.dllewellyn.coinbaseapi.models.currency.CurrencyValue
import com.dllewellyn.coinbaseapi.models.trade.CurrencyBuyAndSell
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder
import io.reactivex.Single

class CurrencyPriceAdapter(private val retrofitApiBuilder: RetrofitApiBuilder) : CurrencyPrice {

    override fun getCurrencyBuyPrice(pair: CurrencyPair) =
        retrofitApiBuilder.getApi().getBuyPrice(pairToString(pair))
            .map { it.data }
            .map {
                CurrencyValue(
                    pair.baseCurrency,
                    it.currency,
                    it.amount.toDouble()
                )
            }

    override fun getCurrencySellPrice(pair: CurrencyPair) =
        retrofitApiBuilder.getApi().getSellPrice(pairToString(pair))
            .map { it.data }
            .map {
                CurrencyValue(
                    it.base,
                    it.currency,
                    it.amount.toDouble()
                )
            }


    /**
     * The ask price is what sellers are willing to take for it. If you are selling a stock, you are going to get the bid price,
     * if you are buying a stock you are going to get the ask price.
     */
    override fun getCurrencyBuyAndSellPrice(pair: CurrencyPair) : Single<CurrencyBuyAndSell> {
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

    fun pairToString(pair: CurrencyPair): String {
        return "${pair.baseCurrency}-${pair.quoteCurrency}"
    }
}