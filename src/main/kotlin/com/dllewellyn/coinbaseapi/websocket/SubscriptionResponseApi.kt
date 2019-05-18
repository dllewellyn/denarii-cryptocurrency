package com.dllewellyn.coinbaseapi.websocket

import com.google.gson.annotations.SerializedName

data class ChannelApi(
    @SerializedName("name") val name : String,
    @SerializedName("product_ids") val productIds : List<String>
)

data class SubscriptionResponseApi(
    @SerializedName("type") val type : String,
    @SerializedName("channels") val channels : List<ChannelApi>
)