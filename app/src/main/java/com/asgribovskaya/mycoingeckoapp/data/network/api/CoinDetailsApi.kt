package com.asgribovskaya.mycoingeckoapp.data.network.api

import com.asgribovskaya.mycoingeckoapp.data.network.models.CoinDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinDetailsApi {
    @GET("api/v3/coins/{id}")
    suspend fun getCoinDetails(
        @Path("id")
        id: String,

        @Query("localization")
        localization: Boolean = false,

        @Query("tickers")
        tickers: Boolean = false,

        @Query("market_data")
        market_data: Boolean = false,

        @Query("community_data")
        community_data: Boolean = false,

        @Query("developer_data")
        developer_data: Boolean = false,

        @Query("sparkline")
        sparkline: Boolean = false,
    ): Response<CoinDetailsResponse>
}