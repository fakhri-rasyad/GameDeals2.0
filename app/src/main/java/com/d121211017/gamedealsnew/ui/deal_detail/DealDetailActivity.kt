package com.d121211017.gamedealsnew.ui.deal_detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.d121211017.gamedealsnew.R
import com.d121211017.gamedealsnew.databinding.ActivityDealDetailBinding
import com.d121211017.gamedealsnew.makeToast
import com.d121211017.gamedealsnew.ui.ViewModelFactory
import com.d121211017.gamedealsnew.ui.recyclerViewAdapter.DealDetailAdapter

class DealDetailActivity : AppCompatActivity() {
    private var _binding: ActivityDealDetailBinding? = null
    private val binding get() = _binding

    private var viewModel: DealDetailViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDealDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        viewModel = getViewModel(this)

        val gameId = intent.getStringExtra(DEAL_ID) ?: ""
        viewModel?.getGameDetail(gameId)

        val dealAdapter = DealDetailAdapter { dealId ->
            val intent = Intent(this, DealDetailActivity::class.java)
            intent.putExtra(DEAL_ID, dealId)
            startActivity(intent)
        }

        binding?.apply {
            cheaperDealsRv.apply {
                adapter = dealAdapter
                layoutManager = LinearLayoutManager(this@DealDetailActivity)
            }
            backButton.setOnClickListener {
                finish()
            }
        }


        viewModel?.apply {
            dealDetail.observe(this@DealDetailActivity) {
                binding?.apply {
                    val otherDealExist = it?.cheaperStores?.isEmpty() == true
                    cheaperPriceTitle.visibility = if(otherDealExist) View.INVISIBLE else View.VISIBLE
                    if(otherDealExist) makeToast(this@DealDetailActivity, "This seems to be the cheapest deal!")

                    gamePreview.load(it?.gameInfo?.thumb) {
                        crossfade(true)
                        placeholder(R.drawable.cheapshark_logo)
                    }

                    dealAdapter.submitList(it?.cheaperStores)

                    gameTitle.text = it?.gameInfo?.name
                    gameDiscount.text = getString(R.string.price_template, it?.gameInfo?.salePrice)
                    gamePrice.text = getString(R.string.price_template, it?.gameInfo?.retailPrice)
                }
            }

            error.observe(this@DealDetailActivity) {errorMessage ->
                makeToast(this@DealDetailActivity, errorMessage)
            }

            isLoading.observe(this@DealDetailActivity) {
                binding?.detailPg?.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel = null
    }

    private fun getViewModel(appCompatActivity: AppCompatActivity): DealDetailViewModel {
        val factory = ViewModelFactory.getViewModelInstance(appCompatActivity.application)
        return ViewModelProvider(appCompatActivity, factory)[DealDetailViewModel::class.java]
    }

    companion object {
        const val DEAL_ID = "deal_id"
    }
}