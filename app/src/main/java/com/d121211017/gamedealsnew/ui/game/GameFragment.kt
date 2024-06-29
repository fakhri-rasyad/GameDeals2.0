package com.d121211017.gamedealsnew.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.d121211017.gamedealsnew.data.ResultState
import com.d121211017.gamedealsnew.data.entity.GameSearchResponseItem
import com.d121211017.gamedealsnew.databinding.FragmentGameBinding
import com.d121211017.gamedealsnew.makeToast
import com.d121211017.gamedealsnew.ui.ViewModelFactory
import com.d121211017.gamedealsnew.ui.recyclerViewAdapter.GameListAdapter

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(activity as AppCompatActivity)
        binding.apply {
            searchButton.setOnClickListener {
                val gameTitle = this.gameSearchEditText.text.toString()
                viewModel.getGameList(gameTitle).observe(viewLifecycleOwner){ gameResult ->
                    when(gameResult){
                        is ResultState.Success -> setUpRecyclerView(gameResult.data as List<GameSearchResponseItem>)
                        is ResultState.Loading -> showLoading(true)
                        is ResultState.Error -> showError(gameResult.message)
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView(gameItemList: List<GameSearchResponseItem>){
        binding.apply {
            showLoading(false)
            val gameGridLayoutManager = GridLayoutManager(requireContext(), 2)
            val gameAdapter = GameListAdapter(gameItemList)
            gameRv.apply {
                adapter = gameAdapter
                layoutManager = gameGridLayoutManager
            }
        }
    }

    private fun showLoading(isLoading: Boolean){
        binding.gamePg.visibility = if(isLoading) View.VISIBLE else View.INVISIBLE
    }

    private fun showError(message: String){
        showLoading(false)
        makeToast(requireContext(), message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getViewModel(appCompatActivity: AppCompatActivity) : GameViewModel {
        val factory = ViewModelFactory.getViewModelInstance(application = appCompatActivity.application)
        return ViewModelProvider(appCompatActivity, factory)[GameViewModel::class.java]
    }
}