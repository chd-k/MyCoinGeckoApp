package com.asgribovskaya.mycoingeckoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.asgribovskaya.mycoingeckoapp.data.network.models.CoinDetailsResponse
import com.asgribovskaya.mycoingeckoapp.data.network.repositories.CoinDetailsRepository
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiError
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiLoading
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiSuccess
import com.asgribovskaya.mycoingeckoapp.databinding.FragmentCoinDetailsBinding
import com.asgribovskaya.mycoingeckoapp.ui.viewmodels.CoinDetailsViewModel
import kotlinx.coroutines.launch



class CoinDetailsFragment : Fragment() {

    private var _binding: FragmentCoinDetailsBinding? = null
    private val binding
        get() = _binding!!

    private val coinDetailsRepository = CoinDetailsRepository()

    private val args: CoinDetailsFragmentArgs by navArgs()

    private val viewModel: CoinDetailsViewModel by viewModels {
        CoinDetailsViewModel.Factory(coinDetailsRepository, args.coinId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.coinDetails.collect { apiResponse ->
                    when(apiResponse) {
                        is ApiSuccess -> {
                            binding.pbCoinDetailsLoading.visibility = INVISIBLE
                            updateUi(apiResponse.data)
                        }
                        is ApiError -> {
                            binding.pbCoinDetailsLoading.visibility = INVISIBLE
                            findNavController().navigate(
                                CoinDetailsFragmentDirections.actionGlobalErrorFragment()
                            )
                        }
                        is ApiLoading -> {
                            binding.pbCoinDetailsLoading.visibility = VISIBLE
                        }
                    }
                }
            }
        }

    }

    private fun updateUi(data: CoinDetailsResponse) {
        binding.ivCoinDetailsImage.load(data.image.large) {
            transformations(CircleCropTransformation())
        }
        binding.tvCoinDetailsCategoriesBody.text = data.categories.joinToString(", ")
        binding.tvCoinDetailsDescriptionBody.text = data.description.en
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}