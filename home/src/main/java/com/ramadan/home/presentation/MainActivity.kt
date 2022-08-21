@file:OptIn(ExperimentalMaterialApi::class)

package com.ramadan.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.ramadan.home.presentation.components.TrianglzScaffold
import com.ramadan.home.theme.AppTheme
import com.ramadan.home.theme.TrianglzShape
import com.ramadan.home.theme.TrianglzTheme
import com.ramadan.home.utils.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()

        setContent {
            val navController = rememberNavController()
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
                        TrianglzScaffold(scaffoldState = scaffoldState) {
                            NavigationGraph(
                                navController = navController,
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
        Box(modifier = Modifier.padding(vertical = 24.dp, horizontal = 24.dp)) {
        }
    }
}