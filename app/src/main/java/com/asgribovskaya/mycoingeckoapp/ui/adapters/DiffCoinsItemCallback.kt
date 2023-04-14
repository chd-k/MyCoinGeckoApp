package com.asgribovskaya.mycoingeckoapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.asgribovskaya.mycoingeckoapp.data.network.models.CoinsListResponseItem

object DiffCoinsItemCallback : DiffUtil.ItemCallback<CoinsListResponseItem>() {
    override fun areItemsTheSame(
        oldItem: CoinsListResponseItem,
        newItem: CoinsListResponseItem,
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CoinsListResponseItem,
        newItem: CoinsListResponseItem,
    ) = oldItem == newItem
}