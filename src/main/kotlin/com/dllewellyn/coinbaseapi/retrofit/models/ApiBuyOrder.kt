package com.dllewellyn.coinbaseapi.retrofit.models

data class ApiBuyOrder(
    val price: String,
    val product_id: String,
    val side: String,
    val size: String,
    val stop : String? = null,
    val stop_price : String? = null
)