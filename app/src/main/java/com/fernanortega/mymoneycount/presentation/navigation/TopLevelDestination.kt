package com.fernanortega.mymoneycount.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.Summarize
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.ManageAccounts
import androidx.compose.material.icons.rounded.Summarize
import androidx.compose.ui.graphics.vector.ImageVector
import com.fernanortega.mymoneycount.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    @StringRes val titleTextId: Int
) {
    CURRENT_REGISTER(
        selectedIcon = Icons.Rounded.Summarize,
        unSelectedIcon = Icons.Outlined.Summarize,
        titleTextId = R.string.summary_label
    ),
    ACCOUNTS(
        selectedIcon = Icons.Rounded.ManageAccounts,
        unSelectedIcon = Icons.Outlined.ManageAccounts,
        titleTextId = R.string.accounts_label
    ),
    REGISTER_SUMMARIZE(
        selectedIcon = Icons.Rounded.CalendarMonth,
        unSelectedIcon = Icons.Outlined.CalendarMonth,
        titleTextId = R.string.register_summarize_label
    )
}