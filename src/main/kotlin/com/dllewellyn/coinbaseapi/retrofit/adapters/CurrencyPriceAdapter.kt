package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.CurrencyPrice
import com.dllewellyn.coinbaseapi.models.CurrencyBuyAndSell
import com.dllewellyn.coinbaseapi.models.CurrencyPair
import com.dllewellyn.coinbaseapi.models.CurrencyValue
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
                    it.amount.toFloat()
                )
            }

    override fun getCurrencySellPrice(pair: CurrencyPair) =
        retrofitApiBuilder.getApi().getSellPrice(pairToString(pair))
            .map { it.data }
            .map {
                CurrencyValue(
                    it.base,
                    it.currency,
                    it.amount.toFloat()
                )
            }

    override fun getCurrencyBuyAndSellPrice(pair: CurrencyPair) : Single<CurrencyBuyAndSell> {
        return retrofitApiBuilder.getApi().getSellPrice(pairToString(pair))
            .mergeWith(retrofitApiBuilder.getApi().getBuyPrice(pairToString(pair)))
            .map { it.data }
            .map {
                CurrencyValue(
                    it.base,
                    it.currency,
                    it.amount.toFloat()
                )
            }
            .toList()
            .map {
                CurrencyBuyAndSell(
                    it.first().currencyFrom,
                    it.first().currencyTo,
                    it.last().amount,
                    it.first().amount
                )
            }
    }

    fun pairToString(pair: CurrencyPair): String {
        return "${pair.baseCurrency}-${pair.quoteCurrency}"
    }
}