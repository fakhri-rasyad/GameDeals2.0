package com.d121211017.gamedealsnew.data.repository

import com.d121211017.gamedealsnew.data.retrofit.ApiService

class GameDealsRepository(
    private val apiService: ApiService
) {
    suspend fun getDealsList() = apiService.getCurrentDeals()

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