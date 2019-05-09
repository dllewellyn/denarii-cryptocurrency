package com.dllewellyn.coinbaseapi.retrofit.models

import com.google.gson.annotations.SerializedName

data class ApiBuyValueResponse(
    @SerializedName("data") val data : ApiBuyValue
)

data class ApiBuyValue(
    @SerializedName("amount") val amount : String,
    @SerializedName("cryptoCurrency") val currency : String
)