package com.d121211017.gamedealsnew.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.d121211017.gamedealsnew.data.entity.DealListItem
import com.d121211017.gamedealsnew.databinding.FragmentHomeBinding
import com.d121211017.gamedealsnew.ui.ViewModelFactory
import com.d121211017.gamedealsnew.ui.loadStateAdapter.LoadingStateAdapter
import com.d121211017.gamedealsnew.ui.recyclerViewAdapter.HomeListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel :HomeViewModel
    private var homeAdapter : HomeListAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeAdapter = HomeListAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(activity as AppCompatActivity)
        val gridLayoutManager = GridLayoutManager(activity, 2)
        binding.homeRv.layoutManager = gridLayoutManager

        homeAdapter?.let {
            setUpRecyclerView(it)
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.deals.collectLatest { dealList ->
                    updateRecyclerList(dealList, it)
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                it.loadStateFlow.collectLatest { loadStates ->
                    binding.homePg.visibility = if (loadStates.refresh is LoadState.Loading) View.VISIBLE else View.INVISIBLE
                }
            }
        }

//        homeAdapter?.let { setUpRecyclerView(it) }
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.deals.collectLatest {
//                homeAdapter?.let { it1 -> updateRecyclerList(it, it1) }
//            }
//            homeAdapter?.loadStateFlow?.collectLatest { pagingLoadStates ->
//                binding.homePg.visibility = if(pagingLoadStates.refresh is LoadState.Loading) View.INVISIBLE else View.VISIBLE
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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

    private fun getViewModel(appCompatActivity: AppCompatActivity) : HomeViewModel{
        val factory = ViewModelFactory.getViewModelInstance(application = appCompatActivity.application)
        return ViewModelProvider(appCompatActivity, factory)[HomeViewModel::class.java]
    }

    companion object {
        private const val TAG = "Home Fragment"
    }

}