package com.d121211017.gamedealsnew.ui.filter

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.d121211017.gamedealsnew.R
import com.d121211017.gamedealsnew.data.entity.DealFilter
import com.d121211017.gamedealsnew.data.entity.Images
import com.d121211017.gamedealsnew.data.entity.StoresItem
import com.d121211017.gamedealsnew.databinding.FragmentFilterBinding
import com.d121211017.gamedealsnew.ui.MainActivity
import com.d121211017.gamedealsnew.ui.ViewModelFactory
import com.d121211017.gamedealsnew.ui.custom_components.CustomArrayAdapter
import com.d121211017.gamedealsnew.ui.home.HomeFragment
import com.d121211017.gamedealsnew.ui.home.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterFragment : BottomSheetDialogFragment() {
    private var _binding : FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(activity as AppCompatActivity)
        val sortBy = resources.getStringArray(R.array.sort_by)
        val storesList = viewModel.getPlaceholderData()
        val dealFilter = viewModel.getFilterData()

        setPreviousFilterData(dealFilter)

        binding.apply {
            gamestoreList.adapter = CustomArrayAdapter(
                requireContext(),
                storesList
            )

            sortSpinner.adapter = ArrayAdapter(
                requireContext(),
                androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,
                sortBy)

            priceSlider.addOnChangeListener{_, _, _ ->
                val values = priceSlider.values
                priceRangeValue.text = getString(R.string.price_range_value, values[0].toInt().toString(), values[1].toInt().toString())
            }

            applyButton.setOnClickListener {
                val newFilterData = DealFilter(
                    title = gameTitleInput.text.toString(),
                    lowerPrice = priceSlider.values[0].toInt(),
                    upperPrice = priceSlider.values[1].toInt(),
                    sortBy = sortSpinner.selectedItem.toString(),
                    storeID = (gamestoreList.selectedItem as StoresItem).storeID
                )
                viewModel.setFilterData(newFilterData)
                dismiss()
            }

            resetButton.setOnClickListener {
              viewModel.clearFilterData()
                dismiss()
            }
        }
    }

    private fun getViewModel(appCompatActivity: AppCompatActivity) : HomeViewModel{
        val factory = ViewModelFactory.getViewModelInstance(application = appCompatActivity.application)
        return ViewModelProvider(appCompatActivity, factory)[HomeViewModel::class.java]
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setPreviousFilterData(dealFilter: DealFilter){
        binding.apply {
            val sortBy = resources.getStringArray(R.array.sort_by)
            this.gameTitleInput.setText(dealFilter.title)
            this.sortSpinner.setSelection(sortBy.indexOf(dealFilter.sortBy))

            val lowerPrice = dealFilter.lowerPrice ?: 0
            val upperPrice = dealFilter.upperPrice ?: 50

            this.priceSlider.setValues(lowerPrice.toFloat(), upperPrice.toFloat())
            priceRangeValue.text = getString(R.string.price_range_value, lowerPrice.toString(), upperPrice.toString())
        }
    }

}