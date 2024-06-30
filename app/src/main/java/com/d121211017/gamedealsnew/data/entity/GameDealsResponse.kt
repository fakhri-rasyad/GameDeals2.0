package com.d121211017.gamedealsnew.data.entity

import com.google.gson.annotations.SerializedName

data class GameDealsResponse(

	@field:SerializedName("cheapestPriceEver")
	val cheapestPriceEver: CheapestPriceEver? = null,

	@field:SerializedName("deals")
	val deals: List<DealsItem?>? = null,

	@field:SerializedName("info")
	val info: Info? = null
)

data class Info(

	@field:SerializedName("steamAppID")
	val steamAppID: String? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class DealsItem(

	@field:SerializedName("dealID")
	val dealID: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("savings")
	val savings: String? = null,

	@field:SerializedName("storeID")
	val storeID: String? = null,

	@field:SerializedName("retailPrice")
	val retailPrice: String? = null
)

data class CheapestPriceEver(

	@field:SerializedName("date")
	val date: Int? = null,

	@field:SerializedName("price")
	val price: String? = null
)
