package com.dllewellyn.coinbaseapi.retrofit.models

import com.google.gson.annotations.SerializedName

data class ApiProductOrderBook(
    @SerializedName("asks") val asks: List<Any>,
    @SerializedName("bids") val bids: List<Any>,
    @SerializedName("sequence") val sequence: String
)