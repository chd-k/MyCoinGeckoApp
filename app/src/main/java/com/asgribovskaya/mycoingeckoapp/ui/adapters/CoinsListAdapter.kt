package com.asgribovskaya.mycoingeckoapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.asgribovskaya.mycoingeckoapp.data.network.models.CoinsListResponseItem
import com.asgribovskaya.mycoingeckoapp.databinding.ItemCoinsListBinding

class CoinsListAdapter : RecyclerView.Adapter<CoinsListViewHolder>() {

    private var onItemClickListener: ((CoinsListResponseItem) -> Unit)? = null

    val listDiffer = AsyncListDiffer(this, DiffCoinsItemCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCoinsListBinding.inflate(inflater, parent, false)
        return CoinsListViewHolder(binding)
    }

    override fun getItemCount() = listDiffer.currentList.size

    override fun onBindViewHolder(holder: CoinsListViewHolder, position: Int) {
        val coin = listDiffer.currentList[position]
        holder.binding.apply {
            root.setOnClickListener {
                onItemClickListener?.let { it(coin) }
            }
            ivItemCoinsListIcon.load(coin.image) {
                transformations(CircleCropTransformation())
            }
            tvItemCoinsListName.text = coin.name
            tvItemCoinsListSymbol.text = coin.symbol
            tvItemCoinsListPrice.text = coin.currentPrice.toString()
            tvItemCoinsListChange.text = coin.priceChange24h.toString()
        }
    }

    fun setOnItemClickListener(listener: (CoinsListResponseItem) -> Unit) {
        onItemClickListener = listener
    }
}