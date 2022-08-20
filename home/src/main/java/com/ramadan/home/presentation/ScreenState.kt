package com.ramadan.home.presentation

data class ScreenState(
    val isLoading: Boolean = true,
    val items: List<String> = emptyList(),
    val error: String = "",
)