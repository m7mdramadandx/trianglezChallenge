package com.ramadan.home.utils

sealed class Screen(val route: String) {

    object Home : Screen(route = "home")
    object HomeDetails : Screen(route = "homeDetails")
}