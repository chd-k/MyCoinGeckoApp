package com.asgribovskaya.mycoingeckoapp.data.network.models


import com.google.gson.annotations.SerializedName

data class Roi(
    @SerializedName("times")
    val times: Double,

    @SerializedName("currency")
    val currency: String,

    @SerializedName("percentage")
    val percentage: Double
)