package com.ramadan.home.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramadan.home.theme.Color
import com.ramadan.home.theme.TrianglzColors
import com.ramadan.home.theme.TrianglzShape
import com.ramadan.home.theme.Typography


@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()

    val colors = if (darkTheme) Color.DarkColorPalette else Color.LightColorPalette
    val TrianglzColors =
        if (darkTheme) Color.TrianglzDarkColorPalette else Color.TrianglzLightColorPalette

    SideEffect {
        systemUiController.setStatusBarColor(color = TrianglzColors.appBar)
        systemUiController.setSystemBarsColor(color = TrianglzColors.appBar)
        systemUiController.setNavigationBarColor(color = colors.onPrimary)
        systemUiController.navigationBarDarkContentEnabled = true
    }

    ProvideTrianglzColors(TrianglzColors) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = TrianglzShape.myShapes,
            content = content
        )
    }
}

@Composable
private fun ProvideTrianglzColors(
    colors: TrianglzColors,
    content: @Composable () -> Unit,
) {
    val TrianglzColors = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colors]
        // provided, and overwrite the values set in it.
        colors.copy()
    }
    TrianglzColors.update(colors)
    CompositionLocalProvider(Color.localTrianglzColors provides TrianglzColors, content = content)

}