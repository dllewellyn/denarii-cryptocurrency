package com.dllewellyn.coinbaseapi.models.currency

/**
 * From the api docs:
 *
 * The base_min_size and base_max_size fields define the min and max order size.
 * The quote_increment field specifies the min order price as well as the price increment.
 * The order price must be a multiple of this increment (i.e. if the increment is 0.01, order prices
 * of 0.001 or 0.021 would be rejected).
 */
data class CurrencyPair(
    val baseCurrency: String,
    val baseMaxSize: String ? = "",
    val baseMinSize: String ? = "",
    val id: String,
    val quoteCurrency: String,
    val quoteIncrement: String ?= ""
) {
    fun containsCurrency(vararg supportedCurrency: SupportedCurrency) = supportedCurrency.any {
        baseCurrency == it.id || quoteCurrency == it.id
    }

    companion object {
        fun fromId(id: String) = with(id.split("-")) {
            CurrencyPair(baseCurrency = first(), id = id, quoteCurrency = last())
        }
    }
}