package com.d121211017.gamedealsnew.ui.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.d121211017.gamedealsnew.R
import com.d121211017.gamedealsnew.data.entity.DealListItem
import com.d121211017.gamedealsnew.databinding.HomeListItemBinding

class HomeListAdapter(
) : PagingDataAdapter<DealListItem, HomeListAdapter.ViewHolder>(
    DIFF_CALLBACK) {
    inner class ViewHolder(binding: HomeListItemBinding) : RecyclerView.ViewHolder(binding.root){
        val gameThumb = binding.gameThumb
        val storeIcon = binding.storeIcon
        val gameTitle = binding.gameTitle
        val gamePrice = binding.gamePrice
        val gameDiscountPrice = binding.gamePriceDiscount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return snapshot().size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dealItem = getItem(position)
        if(dealItem != null){
            holder.apply {
                gamePrice.text = dealItem.normalPrice
                gameDiscountPrice.text = dealItem.salePrice
                gameTitle.text = dealItem.title
                gameThumb.load(dealItem.thumb){
                    crossfade(true)
                    transformations(RoundedCornersTransformation(radius = 12.0F))
                }
                storeIcon.load(
                    holder.itemView.context.getString(
                        R.string.store_banner_template,
                        dealItem.storeID
                    )
                ){
                    crossfade(true)
                }
            }


        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DealListItem>(){
            override fun areItemsTheSame(oldItem: DealListItem, newItem: DealListItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DealListItem, newItem: DealListItem): Boolean {
                return oldItem.dealID == newItem.dealID
            }

        }
    }
}