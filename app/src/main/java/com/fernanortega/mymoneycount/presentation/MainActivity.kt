package com.fernanortega.mymoneycount.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fernanortega.mymoneycount.presentation.navigation.MyMoneyNavHost
import com.fernanortega.mymoneycount.presentation.ui.components.MyMoneyBottomNavBar
import com.fernanortega.mymoneycount.presentation.ui.components.MyMoneyNavRail
import com.fernanortega.mymoneycount.presentation.ui.components.MyMoneyTopAppBar
import com.fernanortega.mymoneycount.presentation.ui.theme.MyMoneyCountTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.collections.immutable.toImmutableList

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            val appState = rememberMyMoneyAppState(
                windowSizeClass = windowSizeClass
            )
            MyMoneyCountTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        bottomBar = {
                            if(appState.shouldShowBottomBar) {
                                MyMoneyBottomNavBar(
                                    destinations = appState.destinations.toImmutableList(),
                                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                                    currentDestination = appState.currentDestination
                                )
                            }
                        }
                    ) { innerPadding ->
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .consumeWindowInsets(innerPadding)
                        ) {
                            if(!appState.shouldShowBottomBar) {
                                MyMoneyNavRail(
                                    destinations = appState.destinations.toImmutableList(),
                                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                                    currentDestination = appState.currentDestination
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                val destination = appState.currentTopLevelDestination
                                if(destination != null) {
                                    MyMoneyTopAppBar(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        title = stringResource(id = destination.titleTextId),
                                        onSearchClick = appState::navigateToGlobalSearch
                                    )
                                }
                                MyMoneyNavHost(
                                    modifier = Modifier
                                        .weight(1f),
                                    appState = appState
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

