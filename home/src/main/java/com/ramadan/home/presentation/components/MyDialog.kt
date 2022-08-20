package com.ramadan.home.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ramadan.home.theme.TrianglzTheme


@Composable
fun CustomDialog(
    openDialogCustom: MutableState<Boolean>,
    isSingleAction: Boolean = false,
    title: String,
    body: String,
    positiveButtonText: String,
    negativeButtonText: String = "",
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit = {},
) {
    if (openDialogCustom.value) {
        Dialog(onDismissRequest = {
            openDialogCustom.value = false
        }) {
            Card(
                shape = RoundedCornerShape(30.dp),
                backgroundColor = TrianglzTheme.colors.popUp,
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 32.dp)
                ) {

                    //.......................................................................

                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .fillMaxWidth(),
                    )
                    Text(
                        text = body,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth(),
                        color = TrianglzTheme.colors.textPrimary,
                        fontSize = 12.sp,
                    )

                    //.......................................................................
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        MyButton(
                            modifier = Modifier.weight(1f),
                            buttonText = positiveButtonText,
                            textColor = Color.White,
                        ) {
                            openDialogCustom.value = false
                            onPositiveClick.invoke()
                        }

                        if (!isSingleAction) {
                            Spacer(Modifier.width(8.dp))
                            MyButton(
                                modifier = Modifier.weight(1f),
                                buttonText = negativeButtonText,
                                textColor = TrianglzTheme.colors.textPrimary,
                                buttonBackground = TrianglzTheme.colors.buttonSecondary,
                            ) {
                                openDialogCustom.value = false
                                onNegativeClick.invoke()
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(name = "Custom Dialog")
@Composable
fun MyDialogUIPreview() {
    CustomDialog(
        openDialogCustom = mutableStateOf(true),
        isSingleAction = true,
        title = "",
        body = "EngString.UPDATE_THE_LATEST_VERSION",
        positiveButtonText = "EngString.UPDATE_NOW",
        negativeButtonText = "EngString.SKIP",
        onPositiveClick = {},
    )
}