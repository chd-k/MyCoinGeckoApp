package com.asgribovskaya.mycoingeckoapp.data.network.api

import com.asgribovskaya.mycoingeckoapp.data.network.models.CoinsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinsListApi {
    @GET("api/v3/coins/markets")
    suspend fun getCoinsList(
       @Query("vs_currency")
       currency: String,

       @Query("per_page")
       items: Int = 30,

       @Query("page")
       page: Int = 1,

       @Query("sparkline")
       sparkline: Boolean = false,

       @Query("price_change_percentage")
       change: String = "24h",

       @Query("locale")
       locale: String = "en",
    ): Response<CoinsListResponse>
}