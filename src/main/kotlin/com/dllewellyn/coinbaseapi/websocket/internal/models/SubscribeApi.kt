package com.dllewellyn.coinbaseapi.websocket.internal.models

import com.google.gson.annotations.SerializedName

data class SubscribeApi(@SerializedName("type") val type : String = "subscribe",
                        @SerializedName("product_ids") val product_ids : List<String>,
                        @SerializedName("channels") val channels : List<String>)