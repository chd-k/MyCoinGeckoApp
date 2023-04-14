package com.asgribovskaya.mycoingeckoapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.asgribovskaya.mycoingeckoapp.data.network.models.CoinsListResponse
import com.asgribovskaya.mycoingeckoapp.data.network.models.CoinsListResponseItem
import com.asgribovskaya.mycoingeckoapp.data.network.repositories.CoinsListRepository
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiLoading
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoinsListViewModel(
    private val coinsListRepository: CoinsListRepository,
) : ViewModel() {

    private val _coinsList = MutableStateFlow<ApiResponse<CoinsListResponse>>(ApiLoading())
    val coinsList: StateFlow<ApiResponse<CoinsListResponse>> = _coinsList

    init {
        getCoinsList()
    }

    fun getCoinsList(currency: String = "usd") = viewModelScope.launch {
        _coinsList.value = ApiLoading()
        _coinsList.value = coinsListRepository.getCoinsList(currency)
    }

    class Factory(private val coinsListRepository: CoinsListRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CoinsListViewModel(coinsListRepository) as T
        }
    }
}