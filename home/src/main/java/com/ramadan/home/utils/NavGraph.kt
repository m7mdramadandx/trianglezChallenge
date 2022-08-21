package com.ramadan.home.utils

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ramadan.home.presentation.home.HomeScreen
import com.ramadan.home.presentation.home.HomeViewModel
import com.ramadan.home.presentation.movieDetails.MovieDetailsScreen
import com.ramadan.netwrok.data.model.MovieApiModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    modalBottomSheetState: ModalBottomSheetState,
) {
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(
                navController = navController,
                viewModel = viewModel,
                modalBottomSheetState = modalBottomSheetState
            )
        }

        composable(Screen.MovieDetails.route) {
            val model = navController.previousBackStackEntry
                ?.savedStateHandle?.get<MovieApiModel?>("movieObj")
            model?.let { it1 -> MovieDetailsScreen(it1, navController) }
        }
    }
}