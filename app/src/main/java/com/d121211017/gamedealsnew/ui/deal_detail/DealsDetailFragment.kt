package com.d121211017.gamedealsnew.ui.deal_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.d121211017.gamedealsnew.R
import com.d121211017.gamedealsnew.databinding.FragmentDealsDetailBinding
import com.d121211017.gamedealsnew.ui.ViewModelFactory

class DealsDetailFragment : Fragment() {

    private var param1: String? = null

    private var _binding: FragmentDealsDetailBinding? = null

    private var viewModel : DealDetailViewModel? = null
    private val binding get() =  _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(GAME_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDealsDetailBinding.inflate(layoutInflater, container, false)
        viewModel = getViewModel(activity as AppCompatActivity)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments !== null){
            val gameId = param1
            if (gameId != null) {
                viewModel?.getGameDetail(gameId)
            }
        }

        viewModel?.dealDetail?.observe(viewLifecycleOwner){
            binding.apply {
                imageThumb
                    .load(it?.gameInfo?.thumb){
                        crossfade(true)
                        placeholder(R.drawable.ic_launcher_foreground)
                    }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel = null
        _binding = null
    }

    private fun getViewModel(appCompatActivity: AppCompatActivity) : DealDetailViewModel {
        val viewModelFactory = ViewModelFactory.getViewModelInstance(appCompatActivity.application)
        return ViewModelProvider(appCompatActivity, viewModelFactory)[DealDetailViewModel::class.java]
    }

    companion object {
        const val GAME_ID = "game_id"
    }
}