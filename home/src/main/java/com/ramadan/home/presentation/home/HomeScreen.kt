package com.ramadan.home.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramadan.home.R
import com.ramadan.home.presentation.components.StaggeredVerticalGrid
import com.ramadan.home.presentation.components.TrianglzAppBar
import com.ramadan.home.presentation.components.TrianglzScaffold
import com.ramadan.home.presentation.home.component.ItemMovie
import com.ramadan.home.theme.TrianglzTheme
import com.ramadan.home.utils.Screen
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
    modalBottomSheetState: ModalBottomSheetState
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val screenState by remember {
        mutableStateOf(viewModel.screenState)
    }

    val swipeRefreshState = rememberSwipeRefreshState(screenState.value.isLoading)
    var showMenu by remember { mutableStateOf(false) }

    TrianglzScaffold(scaffoldState = scaffoldState,
        topBar = {
            TrianglzAppBar(
                title = "Popular Movies",
                actions = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sort_descending),
                        modifier = Modifier.clickable {
                            showMenu = !showMenu
                        },
                        tint = TrianglzTheme.colors.title,
                        contentDescription = ""
                    )

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        // Sort by most popular
                        DropdownMenuItem(
                            onClick = {
                                viewModel.sortList(true)
                                showMenu = false
                            }) {
                            Text(text = "Sort by most popular")
                        }

                        // Sort by top rated
                        DropdownMenuItem(
                            onClick = {
                                viewModel.sortList(false)
                                showMenu = false
                            }) {
                            Text(text = "Sort by top rated")
                        }
                    }
                }

            )
        }

    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.getPopularMovies() },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                )
            }
        ) {
            LazyColumn(Modifier.fillMaxSize()) {

                // Items
                screenState.value.items?.let {
                    item {
                        StaggeredVerticalGrid(
                            modifier = Modifier.padding(top = 20.dp, start = 8.dp, end = 8.dp)
                        ) {
                            it.forEach { model ->

                                ItemMovie(
                                    movieApiModel = model,
                                    onItemClick = {
                                        navController.currentBackStackEntry?.savedStateHandle
                                            ?.set("movieObj", model)

                                        navController.navigate(Screen.MovieDetails.route)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        // Error
        if (screenState.value.error.isNotEmpty() && !screenState.value.isLoading) {
            LaunchedEffect(key1 = Unit) {
                coroutineScope.launch {
                    viewModel.getLocalMovies()
                    scaffoldState.snackbarHostState.showSnackbar("something went wrong")
                }
            }
        }
    }

}