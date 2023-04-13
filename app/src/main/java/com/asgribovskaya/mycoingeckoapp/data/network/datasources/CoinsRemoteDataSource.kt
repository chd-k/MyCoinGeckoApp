package com.asgribovskaya.mycoingeckoapp.data.network.datasources

import com.asgribovskaya.mycoingeckoapp.data.network.api.CoinDetailsApi
import com.asgribovskaya.mycoingeckoapp.data.network.api.CoinsListApi
import com.asgribovskaya.mycoingeckoapp.data.network.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoinsRemoteDataSource {

    private val retrofit: Retrofit by lazy {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val coinsListApi: CoinsListApi by lazy { retrofit.create(CoinsListApi::class.java) }

    val coinDetailsApi: CoinDetailsApi by lazy { retrofit.create(CoinDetailsApi::class.java) }
}