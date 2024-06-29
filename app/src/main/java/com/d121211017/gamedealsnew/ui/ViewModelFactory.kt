package com.d121211017.gamedealsnew.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d121211017.gamedealsnew.data.Injection
import com.d121211017.gamedealsnew.ui.deal_detail.DealDetailViewModel
import com.d121211017.gamedealsnew.ui.game.GameViewModel
import com.d121211017.gamedealsnew.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val application: Application) : ViewModelProvider.NewInstanceFactory(){


    companion object {
        @Volatile
        private var ViewModelFactoryInstance : ViewModelFactory? = null

        fun getViewModelInstance(application: Application) : ViewModelFactory{
            return ViewModelFactoryInstance ?: synchronized(this){
                val viewModelFactoryInstance = ViewModelFactory(application)
                ViewModelFactoryInstance = viewModelFactoryInstance
                viewModelFactoryInstance
            }
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(Injection.getRepository(context = application.applicationContext)) as T
        } else if (modelClass.isAssignableFrom(DealDetailViewModel::class.java)){
            return DealDetailViewModel(Injection.getRepository(context = application.applicationContext)) as T
        } else if (modelClass.isAssignableFrom(GameViewModel::class.java)){
            return GameViewModel(Injection.getRepository(context = application.applicationContext)) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass ${modelClass.name}")
    }


}