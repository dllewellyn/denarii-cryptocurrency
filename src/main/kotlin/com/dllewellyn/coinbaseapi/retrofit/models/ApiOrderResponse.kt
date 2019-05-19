package com.dllewellyn.coinbaseapi.retrofit.models

data class ApiOrderResponse(
    val created_at: String,
    val executed_value: String,
    val fill_fees: String,
    val filled_size: String,
    val id: String,
    val post_only: Boolean,
    val price: String,
    val product_id: String,
    val settled: Boolean,
    val side: String,
    val size: String,
    val status: String,
    val stp: String,
    val time_in_force: String,
    val type: String
)