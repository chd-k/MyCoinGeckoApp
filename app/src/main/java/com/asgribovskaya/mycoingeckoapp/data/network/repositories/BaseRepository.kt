package com.asgribovskaya.mycoingeckoapp.data.network.repositories

import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiError
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiResponse
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiSuccess
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    protected suspend fun <T> handleResponse(execute: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val response = execute()
            if (response.isSuccessful) ApiSuccess(response.body()!!)
            else ApiError(response.message())
        } catch (e: HttpException) {
            ApiError("Unexpected response")
        } catch (e: IOException) {
            ApiError("Check your internet connection")
        } catch (e: NullPointerException) {
            ApiError("Problem with response")
        } catch (e: Exception) {
            ApiError("Something went wrong")
        }
    }
}