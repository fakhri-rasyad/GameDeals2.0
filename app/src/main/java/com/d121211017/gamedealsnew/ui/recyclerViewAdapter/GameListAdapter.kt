package com.d121211017.gamedealsnew.ui.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.d121211017.gamedealsnew.data.entity.GameSearchResponse
import com.d121211017.gamedealsnew.data.entity.GameSearchResponseItem
import com.d121211017.gamedealsnew.databinding.GameListItemBinding

class GameListAdapter(private val gameList : List<GameSearchResponseItem>) :
    RecyclerView.Adapter<GameListAdapter.ViewHolder>() {
        class ViewHolder(binding: GameListItemBinding): RecyclerView.ViewHolder(binding.root){
            val gameThumb = binding.gameThumb
            val gameTitle = binding.gameTitle
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gameItem = gameList[position]
        holder.apply {
            gameTitle.text = gameItem.external
            gameThumb.load(gameItem.thumb)
        }
    }
}