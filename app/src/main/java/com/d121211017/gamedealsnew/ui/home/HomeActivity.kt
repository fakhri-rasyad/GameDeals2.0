package com.d121211017.gamedealsnew.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.d121211017.gamedealsnew.data.entity.DealListItem
import com.d121211017.gamedealsnew.databinding.ActivityHomeBinding
import com.d121211017.gamedealsnew.ui.ViewModelFactory
import com.d121211017.gamedealsnew.ui.recyclerViewAdapter.HomeListAdapter
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = getViewModel(this)
        viewModel.getDealList()

        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.homeRv.layoutManager = gridLayoutManager

        lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect{
                when(it){
                    is HomeViewModelState.Success -> setUpRecyclerView(it.dealsList)
                    is HomeViewModelState.Failure -> makeToast()
                    is HomeViewModelState.Loading -> binding.homePg.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUpRecyclerView(dealList : List<DealListItem>){
        binding.apply {
            val adapter = HomeListAdapter(dealList = dealList)
            this.homeRv.adapter = adapter
            this.homePg.visibility = View.INVISIBLE
        }
    }

    private fun makeToast(){
        Toast.makeText(this, "Error no connection", Toast.LENGTH_SHORT).show()
    }

    private fun getViewModel(appCompatActivity: AppCompatActivity) : HomeViewModel{
        val factory = ViewModelFactory.getViewModelInstance(application = appCompatActivity.application)
        return ViewModelProvider(appCompatActivity, factory)[HomeViewModel::class.java]
    }
}