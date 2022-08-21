package com.ramadan.home.utils

sealed class Screen(val route: String) {

    object Home : Screen(route = "home")
    object MovieDetails : Screen(route = "movieDetails")
}