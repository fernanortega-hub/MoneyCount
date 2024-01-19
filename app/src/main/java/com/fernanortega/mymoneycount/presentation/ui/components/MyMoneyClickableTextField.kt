package com.fernanortega.mymoneycount.presentation.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.fernanortega.mymoneycount.util.addErrorChar

@Composable
fun MyMoneyClickableTextField(
    value: String,
    modifier: Modifier = Modifier,
    error: String? = null,
    label: String,
    onClick: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val textFieldIsPressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect(textFieldIsPressed) {
        if (textFieldIsPressed) {
            onClick()
        }
    }

    TextField(
        value = value,
        onValueChange = {},
        singleLine = true,
        readOnly = true,
        interactionSource = interactionSource,
        modifier = modifier,
        supportingText = if (!error.isNullOrBlank()) {
            {
                Text(text = error)
            }
        } else null,
        label = {
            Text(
                text = label + error.addErrorChar()
            )
        }
    )
}