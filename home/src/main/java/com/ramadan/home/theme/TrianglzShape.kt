package com.ramadan.home.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp


object TrianglzShape {

    val myShapes = Shapes(
        small = RoundedCornerShape(12.dp),
        medium = RoundedCornerShape(16.dp),
        large = RoundedCornerShape(28.dp)
    )

    val bottomSheetShape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)

    internal val LocalShapes = staticCompositionLocalOf {
        Shapes(
            small = RoundedCornerShape(size = 15.dp),
            medium = RoundedCornerShape(size = 20.dp),
            large = RoundedCornerShape(size = 25.dp)
        )
    }
}
