package com.ramadan.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.ramadan.home.theme.TrianglzShape
import com.ramadan.home.theme.TrianglzTheme

@ExperimentalComposeUiApi
@Composable
fun MySearchBar(
    modifier: Modifier,
    searchText: String,
    placeholderText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    readOnly: Boolean = false,
    isEnabled: Boolean = true,
    trailingIcon: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    onSearchAction: () -> Unit = {},
    onSearchBarAction: () -> Unit = {}

) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = TrianglzShape.myShapes.large)
            .clickable {

                onSearchBarAction()
            },
        value = searchText,
        onValueChange = onSearchTextChanged,
        readOnly = readOnly,
        enabled = isEnabled,
        placeholder = {
            Text(text = placeholderText, color = TrianglzTheme.colors.textSecondary)
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = TrianglzTheme.colors.textFieldBg,
            textColor = TrianglzTheme.colors.textPrimary,
            cursorColor = TrianglzTheme.colors.textPrimary,
            disabledTextColor = TrianglzTheme.colors.textPrimary,
        ),
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Companion.Search),
        keyboardActions =
        KeyboardActions(onSearch = {
            keyboardController?.hide()
            onSearchAction.invoke()
        }
        )

    )
}