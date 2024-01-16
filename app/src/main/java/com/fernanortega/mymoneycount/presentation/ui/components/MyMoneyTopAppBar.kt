package com.fernanortega.mymoneycount.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fernanortega.mymoneycount.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMoneyTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onSearchClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(id = R.string.search_icon_content_description)
                )
            }
        }
    )
}