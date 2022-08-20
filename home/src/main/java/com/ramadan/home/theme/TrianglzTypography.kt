package com.ramadan.home.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class TrianglzTypography(

    val title1: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
//        fontFamily = FontFamily(Font(R.font.cairo_light, FontWeight.W400, FontStyle.Normal))

    ),
    val title2: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
//        fontFamily = FontFamily(Font(R.font.cairo_light, FontWeight.Bold, FontStyle.Normal))

    ),
    val quote: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        color = Color.White,
//        fontFamily = FontFamily(Font(R.font.cairo_light, FontWeight.W400, FontStyle.Normal))
    ),
    val body: TextStyle = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
//        fontFamily = FontFamily(Font(R.font.cairo_light, FontWeight.Light, FontStyle.Normal))
    ),
    val body1Bold: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
//        fontFamily = FontFamily(Font(R.font.cairo_light, FontWeight.W400, FontStyle.Normal))
    ),
    val body2: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    val body2Bold: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ),
    val caption: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    val captionBold: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
    ),
)

internal val LocalTypography = staticCompositionLocalOf { TrianglzTypography() }
