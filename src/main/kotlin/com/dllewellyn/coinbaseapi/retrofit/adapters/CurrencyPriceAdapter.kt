package com.dllewellyn.coinbaseapi.retrofit.adapters

import com.dllewellyn.coinbaseapi.interfaces.CurrencyPrice
import com.dllewellyn.coinbaseapi.models.CurrencyPair
import com.dllewellyn.coinbaseapi.models.CurrencyValue
import com.dllewellyn.coinbaseapi.retrofit.RetrofitApiBuilder

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
                pair.baseCurrency,
                it.currency,
                it.amount.toDouble()
            )
        }

    private fun pairToString(pair: CurrencyPair): String {
        return pair.id
    }
}