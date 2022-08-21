package com.ramadan.netwrok.data.response

import com.google.gson.annotations.SerializedName
import com.ramadan.netwrok.data.model.MovieApiModel

data class MoviesResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val movieApiModels: List<MovieApiModel>?,
    @SerializedName("total_page") val total_pages: Int?,
    @SerializedName("total_results") val total_results: Int?
)