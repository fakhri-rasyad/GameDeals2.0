package com.d121211017.gamedealsnew.ui.deal_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d121211017.gamedealsnew.data.entity.DealDetailResponse
import com.d121211017.gamedealsnew.data.repository.GameDealsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DealDetailViewModel(private val repository: GameDealsRepository) : ViewModel() {

    private val _dealDetail = MutableLiveData<DealDetailResponse?>()
    val dealDetail : LiveData<DealDetailResponse?> = _dealDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error
    fun getGameDetail(gameId : String){
        val call = repository.getDealDetails(gameId)

        _isLoading.postValue(true)

        call.enqueue(object : Callback<DealDetailResponse>{
            override fun onResponse(
                p0: Call<DealDetailResponse>,
                p1: Response<DealDetailResponse>
            ) {
                _isLoading.postValue(false)
                val response = p1.body()
                if(p1.isSuccessful && response !== null){
                    _dealDetail.postValue(response)
                }
            }

            override fun onFailure(p0: Call<DealDetailResponse>, p1: Throwable) {
                _error.postValue(p1.message)
            }
        })
    }

}