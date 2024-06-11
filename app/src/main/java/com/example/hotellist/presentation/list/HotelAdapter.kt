package com.example.hotellist.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotellist.databinding.HotelItemBinding
import com.example.hotellist.presentation.core.SortStrategy

class HotelAdapter(private val clickListener: (HotelListUiModel) -> Unit) :
    ListAdapter<HotelListUiModel, HotelAdapter.HotelViewHolder>(DIFF_UTIL) {
    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<HotelListUiModel>() {
            override fun areItemsTheSame(
                oldItem: HotelListUiModel, newItem: HotelListUiModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: HotelListUiModel, newItem: HotelListUiModel
            ): Boolean = oldItem == newItem

        }
    }

    inner class HotelViewHolder(private val binding: HotelItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HotelListUiModel, clickListener: (HotelListUiModel) -> Unit) {
            with(binding) {
                tvHotelName.text = item.name
                tvHotelAddress.text = item.address
                tvHotelDistance.text = item.distanceString
                tvHotelStars.text = item.starsString
                tvHotelSuites.text = item.suitesNumString
            }
            itemView.setOnClickListener { clickListener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder =
        HotelViewHolder(
            HotelItemBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) =
        holder.bind(currentList[position], clickListener)

    private var defaultList: List<HotelListUiModel>? = null

    fun restoreDefaultList() {
        if (defaultList != null) {
            submitList(defaultList)
        }
    }

    private var sortStrategy: SortStrategy<HotelListUiModel>? = null

    fun setSortStrategy(strategy: SortStrategy<HotelListUiModel>) {
        this.sortStrategy = strategy
        applySort()
    }

    private fun applySort() {
        if (defaultList == null) {
            defaultList = currentList.toList()
        }
        if (sortStrategy != null) {
            submitList(sortStrategy?.sort(currentList))
        }
    }
}