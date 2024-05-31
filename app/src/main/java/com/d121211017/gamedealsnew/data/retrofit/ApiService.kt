package com.d121211017.gamedealsnew.data.retrofit

import androidx.compose.foundation.pager.PageSize
import com.d121211017.gamedealsnew.data.entity.DealListItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("deals")
    suspend fun getCurrentDeals(
        @Query("storeID") storeID: String? = null,
        @Query("pageNumber") pageNumber : Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("sortBy") sortBy: String? = null,
        @Query("desc") desc: Boolean? = null,
        @Query("lowerPrice") lowerPrice: Int? = null,
        @Query("upperPrice") upperPrice: Int? = null,
        @Query("metacritic") metacritic: Int? = null,
        @Query("title") title: String? = null
    ) : List<DealListItem>
}