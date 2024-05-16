package com.d121211017.gamedealsnew.data.retrofit

import com.d121211017.gamedealsnew.data.entity.DealList
import com.d121211017.gamedealsnew.data.entity.DealListItem
import retrofit2.http.GET

interface ApiService {
    @GET("deals")
    suspend fun getCurrentDeals() : List<DealListItem>
}