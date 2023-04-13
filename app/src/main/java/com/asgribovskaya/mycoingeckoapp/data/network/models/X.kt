package com.asgribovskaya.mycoingeckoapp.data.network.models


import com.google.gson.annotations.SerializedName

data class X(
    @SerializedName("decimal_place")
    val decimalPlace: Any?,
    @SerializedName("contract_address")
    val contractAddress: String
)