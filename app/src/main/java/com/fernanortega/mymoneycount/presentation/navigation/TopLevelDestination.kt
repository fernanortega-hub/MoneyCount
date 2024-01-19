package com.fernanortega.mymoneycount.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Summarize
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Money
import androidx.compose.material.icons.rounded.Summarize
import androidx.compose.ui.graphics.vector.ImageVector
import com.fernanortega.mymoneycount.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val titleTextId: Int
) {
    MONTHLY_SUMMARIZE(
        selectedIcon = Icons.Rounded.Summarize,
        unselectedIcon = Icons.Outlined.Summarize,
        titleTextId = R.string.monthly_summary_label
    ),
    ACCOUNTS(
        selectedIcon = Icons.Rounded.Money,
        unselectedIcon = Icons.Outlined.Money,
        titleTextId = R.string.accounts_label
    ),
    REGISTER_SUMMARIZE(
        selectedIcon = Icons.Rounded.CalendarMonth,
        unselectedIcon = Icons.Outlined.CalendarMonth,
        titleTextId = R.string.register_summarize_label
    );
}