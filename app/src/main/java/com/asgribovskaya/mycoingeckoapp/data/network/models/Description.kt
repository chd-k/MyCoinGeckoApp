package com.asgribovskaya.mycoingeckoapp.data.network.models


import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("en")
    val en: String
)