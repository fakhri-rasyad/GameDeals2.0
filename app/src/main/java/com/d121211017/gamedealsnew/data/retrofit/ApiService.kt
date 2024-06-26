package com.d121211017.gamedealsnew.data.retrofit

import com.d121211017.gamedealsnew.data.entity.DealDetailResponse
import com.d121211017.gamedealsnew.data.entity.DealListItem
import com.d121211017.gamedealsnew.data.entity.GameDealsResponse
import com.d121211017.gamedealsnew.data.entity.GameSearchResponseItem
import retrofit2.Call
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

    @GET("deals")
    fun getDealDetails(
        @Query("id", encoded = true) gameId : String,
    ) : Call<DealDetailResponse>

    @GET("games")
    suspend fun getGameList(
        @Query("title") title: String?
     ) : List<GameSearchResponseItem>

    @GET("games")
    suspend fun getGameDetail(
        @Query("id") id: Int
    ) : GameDealsResponse
}