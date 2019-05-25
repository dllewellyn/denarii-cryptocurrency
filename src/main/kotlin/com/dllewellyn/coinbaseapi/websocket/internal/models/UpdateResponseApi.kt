package com.dllewellyn.coinbaseapi.websocket.internal.models

import com.dllewellyn.coinbaseapi.models.BuyOrSell
import com.dllewellyn.coinbaseapi.models.currency.CurrencyValue
import com.dllewellyn.coinbaseapi.models.EventResponse
import com.google.gson.annotations.SerializedName
import java.lang.IllegalArgumentException

data class UpdateResponseApi(
    @SerializedName("changes") val changes: List<List<String>>,
    @SerializedName("product_id") val product_id: String,
    @SerializedName("type") val type: String
) {
    fun toCore(): List<EventResponse.Level2Update> {

        val returnList = mutableListOf<EventResponse.Level2Update>()

        changes.forEach {
            val buyOrSell = when (it.first()) {
                "buy" -> BuyOrSell.BUY
                "sell" -> BuyOrSell.SELL
                else -> throw IllegalArgumentException()
            }

            returnList.add(EventResponse.Level2Update(
                CurrencyValue(
                    product_id.productIdToPair().first(),
                    product_id.productIdToPair().last(),
                    it[1].toDouble()
                ), buyOrSell))
        }

        return returnList

    }
}