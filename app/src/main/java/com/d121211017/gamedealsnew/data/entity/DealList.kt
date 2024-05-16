package com.d121211017.gamedealsnew.data.entity

import com.google.gson.annotations.SerializedName

data class DealList(

	@field:SerializedName("GameList")
	val gameList: List<DealListItem?>? = null
)

data class DealListItem(

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

	@field:SerializedName("dealID")
	val dealID: String? = null,

	@field:SerializedName("steamRatingCount")
	val steamRatingCount: String? = null,

	@field:SerializedName("metacriticLink")
	val metacriticLink: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("storeID")
	val storeID: String? = null,

	@field:SerializedName("steamAppID")
	val steamAppID: String? = null,

	@field:SerializedName("internalName")
	val internalName: String? = null,

	@field:SerializedName("steamRatingPercent")
	val steamRatingPercent: String? = null,

	@field:SerializedName("dealRating")
	val dealRating: String? = null,

	@field:SerializedName("normalPrice")
	val normalPrice: String? = null,

	@field:SerializedName("lastChange")
	val lastChange: Int? = null,

	@field:SerializedName("savings")
	val savings: String? = null,

	@field:SerializedName("isOnSale")
	val isOnSale: String? = null,

	@field:SerializedName("steamRatingText")
	val steamRatingText: String? = null
)
