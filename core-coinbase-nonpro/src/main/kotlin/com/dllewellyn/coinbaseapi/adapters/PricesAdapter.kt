package com.dllewellyn.coinbaseapi.adapters

import com.dllewellyn.coinbaseapi.http.InternalHttpClient
import com.dllewellyn.coinbaseapi.nonpro.interfaces.Prices
import com.dllewellyn.coinbaseapi.models.ApiValueResponse
import com.dllewellyn.coinbaseapi.models.toCore
import com.dllewellyn.denarii.models.currency.CurrencyPair
import com.dllewellyn.denarii.models.marketinfo.BuyOrSell
import io.ktor.client.request.get

class PricesAdapter(val httpClient: InternalHttpClient) : Prices {
    companion object {
        const val buy = "buy"
        const val sell = "sell"
        const val spot = "spot"
    }

    private fun basePath(pair: CurrencyPair, endPath: String) = "prices/${pair.id}/$endPath"

    override suspend fun getBuyPrice(pair: CurrencyPair) = httpClient.httpClient.get<ApiValueResponse>(
        httpClient.url(basePath(pair, buy))
    ).toCore(BuyOrSell.BUY)


    override suspend fun getSellPrice(pair: CurrencyPair) = httpClient.httpClient.get<ApiValueResponse>(
        httpClient.url(basePath(pair, sell))
    ).toCore(BuyOrSell.SELL)

    override suspend fun getSpotPrice(pair: CurrencyPair) = httpClient.httpClient.get<ApiValueResponse>(
        httpClient.url(basePath(pair, spot))
    ).toCore(BuyOrSell.SPOT)
}