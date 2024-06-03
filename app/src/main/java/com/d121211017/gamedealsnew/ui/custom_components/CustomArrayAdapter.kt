package com.d121211017.gamedealsnew.ui.custom_components

import android.content.Context
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import coil.load
import com.d121211017.gamedealsnew.R
import com.d121211017.gamedealsnew.data.entity.StoresItem
import com.d121211017.gamedealsnew.databinding.StoreDropdownItemBinding

class CustomArrayAdapter(
    context: Context,
    stores : List<StoresItem>,
    ) : ArrayAdapter<StoresItem>(context, 0,stores) {
    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {

        val store = getItem(position)

        val binding = StoreDropdownItemBinding.inflate(LayoutInflater.from(context), parent, false)

        binding.storeName.text = store?.storeName
        binding.storeIcon.load(
            context.getString(R.string.cheapshark_base_url, store?.images?.icon)
        ){
            crossfade(true)
        }

        return binding.root
    }

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }
}