package com.dllewellyn.coinbaseapi.queries

import com.dllewellyn.coinbaseapi.Api
import com.dllewellyn.coinbaseapi.models.currency.CryptoCurrency
import com.dllewellyn.coinbaseapi.models.currency.SupportedCurrency
import java.math.BigDecimal

class PricesAgainstFiat(private val api: Api) {

    suspend fun retrievePricesAgainstFiat(currency: SupportedCurrency) =
        api.currencyPairs().currencyPairsContaining(currency)
            .map {
                api.buyAndSellPrices().getCurrencyBuyAndSellPrice(it)
            }
            .toList()

    suspend fun retrievePricesAgainstCoinbase(currency: CryptoCurrency): Map<CryptoCurrency, BigDecimal> =
        api.exchangeRates().getExchangeRates(currency).map.map {
            Pair(CryptoCurrency.fromString(it.key), it.value)
        }.toMap()

}