package com.ramadan.home.theme

import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.ramadan.home.theme.TrianglzShape.LocalShapes

object TrianglzTheme {
    val colors: TrianglzColors
        @Composable
        @ReadOnlyComposable
        get() = Color.localTrianglzColors.current


    val typography: TrianglzTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    // We use the default material shapes
    val shapes: Shapes
        @ReadOnlyComposable
        @Composable
        get() = LocalShapes.current

}