package com.fernanortega.mymoneycount.presentation.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMoneyDropdownMenu(
    modifier: Modifier = Modifier,
    options: ImmutableList<String>,
    selectedItem: Int,
    onSelectItem: (Int) -> Unit,
    label: String,
    error: String? = null,
    specialOption: (@Composable ColumnScope.() -> Unit)? = null
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            value = if (selectedItem != -1) options[selectedItem] else "",
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            label = {
                Text(text = label)
            },
            isError = error != null,
            supportingText = if(error.isNullOrBlank()) null else {
                {
                    Text(
                        text = error
                    )
                }
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onSelectItem(index)
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            if (specialOption != null) {
                HorizontalDivider()
                specialOption()
            }
        }
    }
}