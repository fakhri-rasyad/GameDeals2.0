package com.d121211017.gamedealsnew.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.d121211017.gamedealsnew.data.entity.DealListItem
import com.d121211017.gamedealsnew.data.paging.HomePagingSource
import com.d121211017.gamedealsnew.data.retrofit.ApiService

class GameDealsRepository(
    private val apiService: ApiService
) {
//    suspend fun getDealsList() = apiService.getCurrentDeals()

    fun getDealsList() : LiveData<PagingData<DealListItem>>{
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                HomePagingSource(apiService)
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var INSTANCE : GameDealsRepository? = null

        fun getInstance(
            apiService: ApiService
        ) : GameDealsRepository {
                return INSTANCE ?: synchronized(this){
                    val instance = GameDealsRepository(apiService = apiService)
                    INSTANCE = instance
                    instance
                }
        }
    }
}