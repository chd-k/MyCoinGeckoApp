package com.asgribovskaya.mycoingeckoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.asgribovskaya.mycoingeckoapp.data.network.repositories.CoinsListRepository
import com.asgribovskaya.mycoingeckoapp.databinding.FragmentCoinsListBinding
import com.asgribovskaya.mycoingeckoapp.ui.viewmodels.CoinsListViewModel

class CoinsListFragment : Fragment() {

    private var _binding: FragmentCoinsListBinding? = null
    private val binding = _binding!!

    private val coinsListRepository = CoinsListRepository()

    private val viewModel: CoinsListViewModel by viewModels {
        CoinsListViewModel.Factory(coinsListRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}