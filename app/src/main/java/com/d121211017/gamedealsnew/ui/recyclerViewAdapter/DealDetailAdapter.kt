package com.d121211017.gamedealsnew.ui.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.d121211017.gamedealsnew.R
import com.d121211017.gamedealsnew.data.entity.CheaperStoresItem
import com.d121211017.gamedealsnew.databinding.DealDetailCheaperDealItemBinding

class DealDetailAdapter(
    private val onDealClick : (String)-> Unit
) : ListAdapter<CheaperStoresItem, DealDetailAdapter.ViewHolder>(DIFF_CALLBACK) {
    inner class ViewHolder(binding: DealDetailCheaperDealItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val storeIcon = binding.storeIcon
        val gameRetailPrice = binding.gamePriceRetail
        val gameDiscountPrice = binding.gamePriceDiscount
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CheaperStoresItem>() {
            override fun areItemsTheSame(oldItem: CheaperStoresItem, newItem: CheaperStoresItem): Boolean {
                return oldItem.dealID == newItem.dealID
            }

            override fun areContentsTheSame(oldItem: CheaperStoresItem, newItem: CheaperStoresItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DealDetailCheaperDealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            gameRetailPrice.text = item.retailPrice
            gameDiscountPrice.text = item.salePrice
            storeIcon.load(
                holder.itemView.context.getString(
                    R.string.store_icon_template,
                    item.storeID
                )
            ) {
                crossfade(true)
                transformations(RoundedCornersTransformation(8f))
            }

            itemView.setOnClickListener {
                onDealClick(item.dealID!!)
            }
        }
    }

}