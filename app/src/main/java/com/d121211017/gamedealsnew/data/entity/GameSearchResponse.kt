package com.d121211017.gamedealsnew.data.entity

import com.google.gson.annotations.SerializedName

data class GameSearchResponse(
	@field:SerializedName("GameSearchResponse")
	val gameSearchResponse: List<GameSearchResponseItem?>? = null
)

data class GameSearchResponseItem(

	@field:SerializedName("gameID")
	val gameID: String? = null,

	@field:SerializedName("steamAppID")
	val steamAppID: Any? = null,

	@field:SerializedName("cheapestDealID")
	val cheapestDealID: String? = null,

	@field:SerializedName("internalName")
	val internalName: String? = null,

	@field:SerializedName("external")
	val external: String? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("cheapest")
	val cheapest: String? = null
)
