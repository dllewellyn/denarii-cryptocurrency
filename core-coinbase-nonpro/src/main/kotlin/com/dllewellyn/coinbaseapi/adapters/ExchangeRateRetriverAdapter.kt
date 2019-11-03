package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.interfaces.ExchangeRateRetriver
import com.dllewellyn.coinbaseapi.models.ApiCurrencyRates
import com.dllewellyn.denarii.models.ExchangeRates
import com.dllewellyn.denarii.models.currency.CryptoCurrency
import com.dllewellyn.denarii.models.currency.SupportedCurrency
import com.dllewellyn.denarii.models.marketinfo.BuyOrSell
import com.dllewellyn.denarii.models.marketinfo.BuySellPrice
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import io.ktor.client.request.get


class ExchangeRateRetriverAdapter(private val httpClient: InternalHttpClient) :
    ExchangeRateRetriver() {

    override suspend fun retrieveData(arg: CryptoCurrency) =
        httpClient.httpClient.get<ApiCurrencyRates>("${httpClient.url("exchange-rates")}?currency=${arg.str}")
            .let {
                ExchangeRates(
                    arg,
                    it.data.rates.mapValues { rate -> BigDecimal.parseString(rate.value, 10) })
            }
            .map.entries.map {
            BuySellPrice(BuyOrSell.SPOT, it.value, SupportedCurrency(it.key), "coinbase")
        }

}