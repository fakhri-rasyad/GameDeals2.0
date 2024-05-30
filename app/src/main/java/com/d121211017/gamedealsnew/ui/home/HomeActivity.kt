package com.d121211017.gamedealsnew.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.d121211017.gamedealsnew.data.entity.DealListItem
import com.d121211017.gamedealsnew.databinding.ActivityHomeBinding
import com.d121211017.gamedealsnew.ui.ViewModelFactory
import com.d121211017.gamedealsnew.ui.loadStateAdapter.LoadingStateAdapter
import com.d121211017.gamedealsnew.ui.recyclerViewAdapter.HomeListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var homeAdapter : HomeListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = getViewModel(this)

        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.homeRv.layoutManager = gridLayoutManager

        homeAdapter = HomeListAdapter()

//        viewModel.deals.observe(this){
//            setUpRecyclerView(it)
//        }

        setUpRecyclerView(homeAdapter)

        lifecycleScope.launch {
            homeAdapter.loadStateFlow.collectLatest {pagingLoadStates ->
                binding.homePg.isVisible = pagingLoadStates.refresh is LoadState.Loading
            }
            viewModel.deals.collectLatest {pagingData ->
                updateRecyclerList(pagingData, homeAdapter)
            }
        }


//        lifecycleScope.launch {
//            viewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect{
//                when(it){
//                    is HomeViewModelState.Success -> setUpRecyclerView(it.dealsList)
//                    is HomeViewModelState.Failure -> makeToast()
//                    is HomeViewModelState.Loading -> binding.homePg.visibility = View.VISIBLE
//                }
//            }
//        }
    }

//    private fun setUpRecyclerView(
//        dealList : PagingData<DealListItem>
//    ){
//        binding.apply {
//            val adapter = HomeListAdapter()
//            this.homeRv.adapter = adapter.withLoadStateFooter(
//                footer = LoadingStateAdapter {
//                    adapter.retry()
//                }
//            )
//            adapter.submitData(lifecycle,dealList)
//            this.homePg.visibility = View.INVISIBLE
//        }
//    }

    private fun setUpRecyclerView(
        adapter: HomeListAdapter
    ){
        binding.apply {
            this.homeRv.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )
        }
    }

    private fun updateRecyclerList(
        dealList : PagingData<DealListItem>,
        adapter: HomeListAdapter
    ) {
        adapter.submitData(lifecycle,dealList)
    }

    private fun makeToast(){
        Toast.makeText(this, "Error no connection", Toast.LENGTH_SHORT).show()
    }

    private fun getViewModel(appCompatActivity: AppCompatActivity) : HomeViewModel{
        val factory = ViewModelFactory.getViewModelInstance(application = appCompatActivity.application)
        return ViewModelProvider(appCompatActivity, factory)[HomeViewModel::class.java]
    }
}