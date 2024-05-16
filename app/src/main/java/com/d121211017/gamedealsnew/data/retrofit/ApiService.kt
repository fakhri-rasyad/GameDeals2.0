package com.d121211017.gamedealsnew.data.retrofit

import com.d121211017.gamedealsnew.data.entity.DealList
import retrofit2.http.GET

interface ApiService {
    @GET("deals")
    suspend fun getCurrentDeals() : DealList
}