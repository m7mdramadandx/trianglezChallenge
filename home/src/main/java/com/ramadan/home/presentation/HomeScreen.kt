package com.ramadan.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel(),
    modalBottomSheetState: ModalBottomSheetState
) {
    val scaffoldState = rememberScaffoldState()

}
