package com.d121211017.gamedealsnew.data.entity

import retrofit2.http.Query

class DealFilter(
    val storeID: String? = null,
    val sortBy: String? = null,
    val desc: Boolean? = null,
    val lowerPrice: Int? = null,
    val upperPrice: Int? = null,
    val title: String? = null,
    val metacritic: Int? = null,
)