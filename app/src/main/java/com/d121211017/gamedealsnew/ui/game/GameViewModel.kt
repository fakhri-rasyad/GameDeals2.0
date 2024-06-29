package com.d121211017.gamedealsnew.ui.game

import androidx.lifecycle.ViewModel
import com.d121211017.gamedealsnew.data.repository.GameDealsRepository

class GameViewModel(private val gameDealsRepository: GameDealsRepository) : ViewModel() {
    fun getGameList(gameName : String) = gameDealsRepository.getGameList(gameName)
}