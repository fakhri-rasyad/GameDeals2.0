package com.d121211017.gamedealsnew.ui.game_detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.d121211017.gamedealsnew.R
import com.d121211017.gamedealsnew.data.ResultState
import com.d121211017.gamedealsnew.data.entity.DealsItem
import com.d121211017.gamedealsnew.data.entity.Info
import com.d121211017.gamedealsnew.databinding.ActivityGameDetailBinding
import com.d121211017.gamedealsnew.makeToast
import com.d121211017.gamedealsnew.ui.ViewModelFactory
import com.d121211017.gamedealsnew.ui.deal_detail.DealDetailActivity
import com.d121211017.gamedealsnew.ui.deal_detail.DealDetailViewModel
import com.d121211017.gamedealsnew.ui.recyclerViewAdapter.DealDetailAdapter
import com.d121211017.gamedealsnew.ui.recyclerViewAdapter.GameDetailAdapter

class GameDetailActivity : AppCompatActivity() {
    private var _binding : ActivityGameDetailBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel : GameDetailViewModel
    private lateinit var dealAdapter : GameDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGameDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = getViewModel(this)

        setUpRecyclerView()

        val gameId = intent.getStringExtra(GAME_ID) ?: ""

        viewModel.getGameDetail(gameId = gameId.toInt()).observe(this){detailResult ->
            when(detailResult){
                is ResultState.Success -> {
                    setupGameDetail(detailResult.data.info ?: Info())
                    submitData(detailResult.data.deals)
                }
                is ResultState.Error -> showError(detailResult.message)
                is ResultState.Loading -> showLoading(true)
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }

    }

    private fun setupGameDetail(gameInfo : Info){
        binding.apply {
            gamePreview.load(
                gameInfo.thumb
            ){
                crossfade(true)
            }
            gameTitle.text = gameInfo.title
        }
    }

    private fun setUpRecyclerView(){
        dealAdapter = GameDetailAdapter { dealId ->
            val intent = Intent(this, DealDetailActivity::class.java)
            intent.putExtra(DealDetailActivity.DEAL_ID, dealId)
            startActivity(intent)
        }
        val rvLayoutManager = LinearLayoutManager(this)

        binding.cheaperDealsRv.apply {
            layoutManager = rvLayoutManager
            adapter = dealAdapter
        }
    }

    private fun submitData(dealList: List<DealsItem?>?){
        showLoading(false)
        dealAdapter.submitList(dealList)
    }

    private fun showLoading(isLoading: Boolean){
        binding.detailPg.visibility = if(isLoading) View.VISIBLE else View.INVISIBLE
    }

    private fun showError(message: String){
        showLoading(false)
        makeToast(this, message)
    }

    private fun getViewModel(appCompatActivity: AppCompatActivity): GameDetailViewModel {
        val factory = ViewModelFactory.getViewModelInstance(appCompatActivity.application)
        return ViewModelProvider(appCompatActivity, factory)[GameDetailViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val GAME_ID = "game_id"
    }
}