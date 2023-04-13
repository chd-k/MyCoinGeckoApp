package com.asgribovskaya.mycoingeckoapp.data.network.repositories

import com.asgribovskaya.mycoingeckoapp.data.network.datasources.CoinsRemoteDataSource
import com.asgribovskaya.mycoingeckoapp.data.network.models.CoinsListResponse
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiResponse

class CoinsListRepository : BaseRepository() {
    suspend fun getCoinsList(currency: String): ApiResponse<CoinsListResponse> =
        handleResponse { CoinsRemoteDataSource.coinsListApi.getCoinsList(currency) }
}