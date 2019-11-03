package com.dllewellyn.coinbaseapi.api.models


import com.dllewellyn.denarii.models.marketinfo.TwentyFourHourStats
import com.google.gson.annotations.SerializedName
import com.ionspin.kotlin.bignum.decimal.BigDecimal

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

fun ApiStats.toCore() =
    TwentyFourHourStats(
        BigDecimal.parseString(`open`, 10),
        BigDecimal.parseString(high, 10),
        BigDecimal.parseString(low, 10),
        BigDecimal.parseString(volume, 10)
    )