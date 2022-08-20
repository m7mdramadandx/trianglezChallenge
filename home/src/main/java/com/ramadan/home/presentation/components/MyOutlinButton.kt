package com.ramadan.home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramadan.home.theme.TrianglzTheme

@Composable
fun MyOutlineButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    testTag: String,
    isEnabled: Boolean = true,
    onClick: () -> (Unit),
) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = TrianglzTheme.colors.popUp,
            contentColor = TrianglzTheme.colors.error
        ),
        border = BorderStroke(1.dp, TrianglzTheme.colors.border),
        enabled = isEnabled,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            disabledElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        modifier = modifier
            .semantics { contentDescription = testTag }
            .height(55.dp)
            .testTag(testTag),
        shape = RoundedCornerShape(28.dp),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = buttonText,
            textAlign = TextAlign.Center,
            color = TrianglzTheme.colors.textPrimary
        )
    }
}