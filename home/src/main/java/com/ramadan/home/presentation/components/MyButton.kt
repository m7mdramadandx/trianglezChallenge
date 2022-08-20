package com.ramadan.home.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramadan.home.theme.TrianglzTheme


@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    padding: Int = 0,
    textColor: Color = TrianglzTheme.colors.textPrimary,
    buttonBackground: Color = TrianglzTheme.colors.button,
    isEnabled: Boolean = true,
    onClick: () -> (Unit)
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            disabledElevation = 0.dp,
//            focusedElevation = 0.dp,
//            hoveredElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        modifier = modifier
            .padding(horizontal = padding.dp)
            .fillMaxWidth()
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = TrianglzTheme.colors.buttonInteractive,
            backgroundColor = buttonBackground
        ),
        shape = RoundedCornerShape(28.dp),
    ) {
        Text(
            text = buttonText,
            color = if (isEnabled) textColor else TrianglzTheme.colors.textPrimary,
            textAlign = TextAlign.Center,
        )
    }
}