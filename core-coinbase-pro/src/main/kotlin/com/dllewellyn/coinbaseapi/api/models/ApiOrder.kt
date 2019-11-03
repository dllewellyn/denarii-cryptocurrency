package com.dllewellyn.coinbaseapi.api.models

import com.dllewellyn.denarii.models.trade.OrderRequest

data class ApiOrder(
    val price: String,
    val product_id: String,
    val side: String,
    val size: String,
    val type : String,
    val stop : String? = null,
    val stop_price : String? = null
)


fun OrderRequest.toApi() =
    ApiOrder(
        this.price.toStringExpanded(),
        this.currency.id,
        this.buyOrSell.v,
        this.size.toStringExpanded(),
        this.type.v
    )

