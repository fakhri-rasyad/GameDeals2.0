package com.d121211017.gamedealsnew.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d121211017.gamedealsnew.data.entity.DealListItem
import com.d121211017.gamedealsnew.data.repository.GameDealsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val gameDealsRepository: GameDealsRepository): ViewModel() {
    private val _uiState = MutableStateFlow<HomeViewModelState>(HomeViewModelState.Loading)

    val uiState: StateFlow<HomeViewModelState> = _uiState.asStateFlow()

    fun getDealList(){
        _uiState.update { HomeViewModelState.Loading }
        viewModelScope.launch {
            try {
                val gameDealsList = gameDealsRepository.getDealsList()
                _uiState.update { HomeViewModelState.Success(dealsList = gameDealsList) }
                Log.d("HomeViewModel", gameDealsList.first().toString())
            } catch(e:Exception) {
                Log.d("HomeViewModel", e.message.toString())
                _uiState.update { HomeViewModelState.Failure }
            }
        }
    }
}

sealed interface HomeViewModelState{
    data object Loading: HomeViewModelState

    data class Success(val dealsList: List<DealListItem>) : HomeViewModelState

    data object Failure: HomeViewModelState
}