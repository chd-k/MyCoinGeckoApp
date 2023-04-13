package com.asgribovskaya.mycoingeckoapp.data.network.utils

sealed interface ApiResponse<T>

class ApiSuccess<T>(val data: T) : ApiResponse<T>

class ApiError<T>(val message: String) : ApiResponse<T>

class ApiLoading<T> : ApiResponse<T>