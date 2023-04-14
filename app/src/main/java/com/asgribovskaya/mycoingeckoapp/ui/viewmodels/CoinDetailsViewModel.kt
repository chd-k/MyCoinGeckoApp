package com.asgribovskaya.mycoingeckoapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.asgribovskaya.mycoingeckoapp.data.network.models.CoinDetailsResponse
import com.asgribovskaya.mycoingeckoapp.data.network.repositories.CoinDetailsRepository
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiLoading
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoinDetailsViewModel(
    private val coinDetailsRepository: CoinDetailsRepository,
    private val coinId: String,
) : ViewModel() {

    private val _coinDetails = MutableStateFlow<ApiResponse<CoinDetailsResponse>>(ApiLoading())
    val coinDetails: StateFlow<ApiResponse<CoinDetailsResponse>> = _coinDetails

    init {
        getCoinDetails(coinId)
    }

    private fun getCoinDetails(id: String) = viewModelScope.launch {
        _coinDetails.value = ApiLoading()
        _coinDetails.value = coinDetailsRepository.getCoinDetails(id)
    }

    class Factory(
        private val coinDetailsRepository: CoinDetailsRepository,
        private val coinId: String,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CoinDetailsViewModel(coinDetailsRepository, coinId) as T
        }
    }
}