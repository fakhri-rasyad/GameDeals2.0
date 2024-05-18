package com.d121211017.gamedealsnew.ui.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.d121211017.gamedealsnew.data.entity.DealListItem
import com.d121211017.gamedealsnew.databinding.HomeListItemBinding

class HomeListAdapter(private val dealList: List<DealListItem>) : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {
    inner class ViewHolder(binding: HomeListItemBinding) : RecyclerView.ViewHolder(binding.root){
        val gameThumb = binding.gameThumb
        val gameTitle = binding.gameTitle
        val gamePrice = binding.gamePrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dealList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dealItem = dealList[position]
        holder.apply {
            this.gamePrice.text = dealItem.salePrice
            this.gameTitle.text = dealItem.title
            this.gameThumb.load(dealItem.thumb){
                crossfade(true)
                transformations(RoundedCornersTransformation(radius = 12.0F))
            }
        }
    }
}