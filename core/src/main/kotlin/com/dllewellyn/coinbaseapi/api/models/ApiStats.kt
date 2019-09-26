package com.dllewellyn.coinbaseapi.api.models


import com.dllewellyn.coinbaseapi.models.marketinfo.TwentyFourHourStats
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ApiStats(
    @SerializedName("open")
    val `open`: String,
    @SerializedName("high")
    val high: String,
    @SerializedName("low")
    val low: String,
    @SerializedName("volume")
    val volume: String
)

fun ApiStats.toCore() = TwentyFourHourStats(BigDecimal(`open`), BigDecimal(high), BigDecimal(low), BigDecimal(volume))