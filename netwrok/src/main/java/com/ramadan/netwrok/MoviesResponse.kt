package com.ramadan.netwrok

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movieApiModels: List<MovieApiModel>,
    @SerializedName("total_page") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
)