package com.d121211017.gamedealsnew.data.entity

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DealDetailResponse(

	@field:SerializedName("cheaperStores")
	val cheaperStores: List<CheaperStoresItem?>? = null,

	@field:SerializedName("cheapestPrice")
	val cheapestPrice: CheapestPrice? = null,

	@field:SerializedName("gameInfo")
	val gameInfo: GameInfo? = null
) : Parcelable

@Parcelize
data class CheaperStoresItem(

	@field:SerializedName("salePrice")
	val salePrice: String? = null,

	@field:SerializedName("dealID")
	val dealID: String? = null,

	@field:SerializedName("storeID")
	val storeID: String? = null,

	@field:SerializedName("retailPrice")
	val retailPrice: String? = null
) : Parcelable

@Parcelize
data class GameInfo(

	@field:SerializedName("gameID")
	val gameID: String? = null,

	@field:SerializedName("metacriticScore")
	val metacriticScore: String? = null,

	@field:SerializedName("salePrice")
	val salePrice: String? = null,

	@field:SerializedName("releaseDate")
	val releaseDate: Int? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("steamRatingCount")
	val steamRatingCount: String? = null,

	@field:SerializedName("steamworks")
	val steamworks: String? = null,

	@field:SerializedName("metacriticLink")
	val metacriticLink: String? = null,

	@field:SerializedName("storeID")
	val storeID: String? = null,

	@field:SerializedName("steamAppID")
	val steamAppID: String? = null,

	@field:SerializedName("steamRatingPercent")
	val steamRatingPercent: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("publisher")
	val publisher: String? = null,

	@field:SerializedName("retailPrice")
	val retailPrice: String? = null,

	@field:SerializedName("steamRatingText")
	val steamRatingText: String? = null
) : Parcelable

@Parcelize
data class CheapestPrice(

	@field:SerializedName("date")
	val date: Int? = null,

	@field:SerializedName("price")
	val price: String? = null
) : Parcelable
