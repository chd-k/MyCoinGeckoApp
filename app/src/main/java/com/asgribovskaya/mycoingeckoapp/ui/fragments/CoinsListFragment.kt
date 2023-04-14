package com.asgribovskaya.mycoingeckoapp.ui.fragments

import android.os.Bundle
import android.util.Log
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asgribovskaya.mycoingeckoapp.data.network.repositories.CoinsListRepository
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiError
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiLoading
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiSuccess
import com.asgribovskaya.mycoingeckoapp.databinding.FragmentCoinsListBinding
import com.asgribovskaya.mycoingeckoapp.ui.adapters.CoinsListAdapter
import com.asgribovskaya.mycoingeckoapp.ui.viewmodels.CoinsListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CoinsListFragment : Fragment() {

    private var _binding: FragmentCoinsListBinding? = null
    private val binding
        get() = _binding!!

    lateinit var coinsListAdapter: CoinsListAdapter

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
        setUpRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpRecyclerView() {
        coinsListAdapter = CoinsListAdapter().apply {
            setOnItemClickListener {
                findNavController().navigate(
                    CoinsListFragmentDirections.actionCoinsListFragmentToCoinDetailsFragment(it.id)
                )
            }
            registerAdapterDataObserver(
                object : RecyclerView.AdapterDataObserver() {
                    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                        super.onItemRangeMoved(fromPosition, toPosition, itemCount)
                        binding.rvCoinsListCoins.scrollToPosition(0)
                    }
                }
            )
        }
        binding.rvCoinsListCoins.apply {
            adapter = coinsListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setUpListeners() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.coinsList.collect { apiResponse ->
                    when (apiResponse) {
                        is ApiSuccess -> {
                            binding.pbCoinsListLoading.visibility = INVISIBLE
                            coinsListAdapter.listDiffer.submitList(apiResponse.data)
                        }
                        is ApiError -> {
                            findNavController().navigate(CoinsListFragmentDirections.actionGlobalErrorFragment())
                        }
                        is ApiLoading -> {
                            binding.pbCoinsListLoading.visibility = VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}