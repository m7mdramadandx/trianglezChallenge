package com.ramadan.home.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ramadan.home.theme.TrianglzTheme

@Composable
fun MyCircularProgressIndicator(size: Dp = 48.dp) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material.CircularProgressIndicator(
            modifier = Modifier
                .width(size)
                .height(size),
            strokeWidth = 2.dp,
            color = TrianglzTheme.colors.secondary
        )
    }
}