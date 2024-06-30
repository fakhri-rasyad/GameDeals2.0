package com.d121211017.gamedealsnew.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.d121211017.gamedealsnew.data.ResultState
import com.d121211017.gamedealsnew.data.entity.DealDetailResponse
import com.d121211017.gamedealsnew.data.entity.DealFilter
import com.d121211017.gamedealsnew.data.entity.DealListItem
import com.d121211017.gamedealsnew.data.entity.GameDealsResponse
import com.d121211017.gamedealsnew.data.entity.GameSearchResponseItem
import com.d121211017.gamedealsnew.data.paging.HomePagingSource
import com.d121211017.gamedealsnew.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException

class GameDealsRepository(
    private val apiService: ApiService
) {
    fun getDealsList(
        dealFilter: DealFilter
    ) : Flow<PagingData<DealListItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                HomePagingSource(apiService, dealFilter)
            }
        ).flow
    }

    fun getDealDetails(gameId: String) : Call<DealDetailResponse> {
        return apiService.getDealDetails(gameId = gameId)
    }

    fun getGameList(gameName: String) : LiveData<ResultState<List<GameSearchResponseItem>>> = liveData {
        emit(ResultState.Loading)
        try{
            val result = apiService.getGameList(title = gameName)
            emit(ResultState.Success(result))
        } catch (e: Exception){
            when(e){
                is HttpException -> {
                    val errorBody = e.response()?.errorBody()?.string()
                    val errorMessage = extractErrorMessage(errorBody)
                    emit(ResultState.Error(errorMessage ?: "An unknown error occured"))
                }
                is IOException -> {
                    emit(ResultState.Error("Network error. Please check your connection and try again."))
                }
                else -> {
                    Log.e("REPOSITORY", e.message.toString())
                    emit(ResultState.Error("An unknown error occured"))
                }
            }
        }
    }

    fun getGameDetail(gameId: Int) : LiveData<ResultState<GameDealsResponse>> = liveData {
        emit(ResultState.Loading)
        try{
            val result = apiService.getGameDetail(id = gameId)
            emit(ResultState.Success(result))
        } catch (e: Exception){
            when(e){
                is HttpException -> {
                    val errorBody = e.response()?.errorBody()?.string()
                    val errorMessage = extractErrorMessage(errorBody)
                    emit(ResultState.Error(errorMessage ?: "An unknown error occured"))
                }
                is IOException -> {
                    emit(ResultState.Error("Network error. Please check your connection and try again."))
                }
                else -> {
                    Log.e("REPOSITORY", e.message.toString())
                    emit(ResultState.Error("An unknown error occured"))
                }
            }
        }
    }

    private fun extractErrorMessage(errorBody: String?): String? {
        return try {
            val json = JSONObject(errorBody)
            json.getString("message")
        } catch (e: JSONException) {
            null
        }
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