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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asgribovskaya.mycoingeckoapp.data.network.repositories.CoinsListRepository
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiError
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiLoading
import com.asgribovskaya.mycoingeckoapp.data.network.utils.ApiSuccess
import com.asgribovskaya.mycoingeckoapp.databinding.FragmentCoinsListBinding
import com.asgribovskaya.mycoingeckoapp.ui.adapters.CoinsListAdapter
import com.asgribovskaya.mycoingeckoapp.ui.viewmodels.CoinsListViewModel
import kotlinx.coroutines.launch

class CoinsListFragment : Fragment() {

    private var _binding: FragmentCoinsListBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var coinsListAdapter: CoinsListAdapter

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
        setUpCollectors()
        setUpChipsListener()
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
                    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                        super.onItemRangeChanged(positionStart, itemCount)
                        binding.rvCoinsListCoins.scrollToPosition(0)
                    }

                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        super.onItemRangeInserted(positionStart, itemCount)
                        binding.rvCoinsListCoins.scrollToPosition(0)
                    }

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

    private fun setUpCollectors() {
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

    private fun setUpChipsListener() {
        binding.chpgCoinsListCurrency.setOnCheckedStateChangeListener { _, checkedIds ->
            //  ChipGroup with single selection
            when (checkedIds[0]) {
                com.asgribovskaya.mycoingeckoapp.R.id.chp_coins_list_usd -> {
                    viewModel.getCoinsList("usd")
                }

                com.asgribovskaya.mycoingeckoapp.R.id.chp_coins_list_eur -> {
                    viewModel.getCoinsList("eur")
                }

                com.asgribovskaya.mycoingeckoapp.R.id.chp_coins_list_rub -> {
                    viewModel.getCoinsList("rub")
                }

                else -> {
                    viewModel.getCoinsList()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}