package com.d121211017.gamedealsnew.data.entity

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
@Parcelize
data class Images(

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("banner")
	val banner: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null
) : Parcelable

@Parcelize
data class StoresItem(

	@field:SerializedName("images")
	val images: Images? = null,

	@field:SerializedName("storeName")
	val storeName: String? = null,

	@field:SerializedName("storeID")
	val storeID: String? = null,

	@field:SerializedName("isActive")
	val isActive: Int? = null
) : Parcelable
