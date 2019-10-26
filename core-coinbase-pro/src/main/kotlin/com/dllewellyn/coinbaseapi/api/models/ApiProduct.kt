package com.dllewellyn.coinbaseapi.api.models

import com.google.gson.annotations.SerializedName

data class ApiProduct(
    @SerializedName("base_currency") val baseCurrency: String,
    @SerializedName("base_max_size") val baseMaxSize: String,
    @SerializedName("base_min_size") val baseMinSize: String,
    @SerializedName("id") val id: String,
    @SerializedName("quote_currency") val quoteCurrency: String,
    @SerializedName("quote_increment") val quoteIncrement: String)