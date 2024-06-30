package com.d121211017.gamedealsnew.ui.game_detail

import androidx.lifecycle.ViewModel
import com.d121211017.gamedealsnew.data.repository.GameDealsRepository

class GameDetailViewModel(private val gameDealsRepository: GameDealsRepository) : ViewModel() {
    fun getGameDetail(gameId: Int) = gameDealsRepository.getGameDetail(gameId)
}