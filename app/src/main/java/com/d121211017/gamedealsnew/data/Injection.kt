package com.d121211017.gamedealsnew.data

import android.content.Context
import com.d121211017.gamedealsnew.data.repository.GameDealsRepository
import com.d121211017.gamedealsnew.data.retrofit.ApiConfig

object Injection {
    fun getRepository(context: Context) : GameDealsRepository{
        val apiService = ApiConfig.getApiService()
        return GameDealsRepository.getInstance(apiService)
    }
}