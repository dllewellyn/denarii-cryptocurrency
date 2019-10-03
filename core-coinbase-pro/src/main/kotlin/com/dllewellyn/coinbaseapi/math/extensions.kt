package com.dllewellyn.coinbaseapi.math

import java.math.BigDecimal
import java.math.RoundingMode

fun round(value: Double, places: Int): Double {
    if (places < 0) throw IllegalArgumentException()

    var bd = BigDecimal(value)
    bd = bd.setScale(places, RoundingMode.HALF_UP)
    return bd.toDouble()
}

fun Double.roundTo(places : Int) = round(this, places)