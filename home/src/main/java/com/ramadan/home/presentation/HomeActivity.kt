@file:OptIn(ExperimentalMaterialApi::class)

package com.ramadan.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.ramadan.home.R
import com.ramadan.home.presentation.components.TrianglzAppBar
import com.ramadan.home.presentation.components.TrianglzScaffold
import com.ramadan.home.theme.AppTheme
import com.ramadan.home.theme.TrianglzShape
import com.ramadan.home.theme.TrianglzTheme
import com.ramadan.home.utils.Screen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            val scaffoldState = rememberScaffoldState()
            val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

            AppTheme {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    ModalBottomSheetLayout(
                        sheetState = modalBottomSheetState,
                        sheetShape = TrianglzShape.bottomSheetShape,
                        sheetBackgroundColor = TrianglzTheme.colors.background,
                        sheetContent = {
                            ModalBottomSheetContent(modalBottomSheetState, scaffoldState)
                        }
                    ) {
                        TrianglzScaffold(
                            scaffoldState = scaffoldState,
                            topBar = { MyTopBar(navController) },
                        ) { innerPadding ->
                            NavigationGraph(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                scaffoldState = scaffoldState,
                                modalBottomSheetState = modalBottomSheetState
                            )
                        }
                    }
                }
            }

        }
    }


    @Composable
    fun ModalBottomSheetContent(
        modalBottomSheetState: ModalBottomSheetState,
        scaffoldState: ScaffoldState,
    ) {
        val focusManager = LocalFocusManager.current
        val scope = rememberCoroutineScope()

        Box(modifier = Modifier.padding(vertical = 24.dp, horizontal = 24.dp)) {
        }

    }

    @Composable
    fun NavigationGraph(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        modalBottomSheetState: ModalBottomSheetState,
    ) {
//        val viewModel: QuoteOfDayViewModel = hiltViewModel()
        NavHost(navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) {
                val viewModel = hiltViewModel<HomeViewModel>()

                HomeScreen(
                    modifier = modifier,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    viewModel = viewModel,
                    modalBottomSheetState = modalBottomSheetState
                )
            }
        }
    }

    @Composable
    fun MyTopBar(navController: NavController) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
        TrianglzAppBar(
            title = stringResource(R.string.app_name),
        )
    }
}