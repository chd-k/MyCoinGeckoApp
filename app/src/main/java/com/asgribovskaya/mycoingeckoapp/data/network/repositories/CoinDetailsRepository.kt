package com.asgribovskaya.mycoingeckoapp.data.network.repositories

import com.asgribovskaya.mycoingeckoapp.data.network.datasources.CoinsRemoteDataSource
import com.asgribovskaya.mycoingeckoapp.data.network.models.CoinDetailsResponse
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiResponse

class CoinDetailsRepository : BaseRepository() {
    suspend fun getCoinDetails(id: String): ApiResponse<CoinDetailsResponse> =
        handleResponse { CoinsRemoteDataSource.coinDetailsApi.getCoinDetails(id) }
}