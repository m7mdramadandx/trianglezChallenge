package com.ramadan.home.presentation.home

import com.ramadan.netwrok.data.model.MovieApiModel

data class ScreenState(
    val isLoading: Boolean = true,
    val items: List<MovieApiModel>? = emptyList(),
    val error: String = "",
)