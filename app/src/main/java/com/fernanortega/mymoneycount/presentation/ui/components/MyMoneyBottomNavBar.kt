package com.fernanortega.mymoneycount.presentation.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavDestination
import com.fernanortega.mymoneycount.presentation.navigation.TopLevelDestination
import com.fernanortega.mymoneycount.presentation.navigation.util.isTopLevelDestinationInHierarchy
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MyMoneyBottomNavBar(
    modifier: Modifier = Modifier,
    destinations: ImmutableList<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
) {
    NavigationBar(
        modifier = modifier
    ) {
        destinations.forEach { topLevelDestination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(topLevelDestination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(topLevelDestination) },
                icon = {
                    Icon(
                        imageVector =  if(!selected) topLevelDestination.unselectedIcon else topLevelDestination.selectedIcon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = topLevelDestination.titleTextId),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    }
}
