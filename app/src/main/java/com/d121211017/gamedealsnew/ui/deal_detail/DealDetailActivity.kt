package com.d121211017.gamedealsnew.ui.deal_detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.d121211017.gamedealsnew.R
import com.d121211017.gamedealsnew.databinding.ActivityDealDetailBinding
import com.d121211017.gamedealsnew.ui.ViewModelFactory
import com.d121211017.gamedealsnew.ui.recyclerViewAdapter.DealDetailAdapter

class DealDetailActivity : AppCompatActivity() {
    private var _binding : ActivityDealDetailBinding? = null
    private val binding get() = _binding

    private var viewModel : DealDetailViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDealDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        viewModel = getViewModel(this)

        val gameId = intent.getStringExtra(DEAL_ID) ?: ""
        viewModel?.getGameDetail(gameId)

        val dealAdapter = DealDetailAdapter {dealId ->
            val intent =  Intent(this, DealDetailActivity::class.java)
            intent.putExtra(DEAL_ID, dealId)
            startActivity(intent)
        }

        binding?.cheaperDealsRv?.apply {
            adapter = dealAdapter
            layoutManager = LinearLayoutManager(this@DealDetailActivity)
        }

        viewModel?.dealDetail?.observe(this){
            binding?.apply {
                gamePreview.load(it?.gameInfo?.thumb){
                    crossfade(true)
                    placeholder(R.drawable.cheapshark_logo)
                }
                backButton.setOnClickListener {
                    finish()
                }

                dealAdapter.submitList(it?.cheaperStores)

                gameTitle.text = it?.gameInfo?.name
                gameDiscount.text = getString(R.string.price_template, it?.gameInfo?.salePrice)
                gamePrice.text = getString(R.string.price_template, it?.gameInfo?.retailPrice)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel = null
    }

    private fun getViewModel(appCompatActivity: AppCompatActivity) : DealDetailViewModel {
        val factory = ViewModelFactory.getViewModelInstance(appCompatActivity.application)
        return ViewModelProvider(appCompatActivity, factory)[DealDetailViewModel::class.java]
    }

    companion object {
        const val DEAL_ID = "deal_id"
    }
}