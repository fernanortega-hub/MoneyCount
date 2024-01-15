package com.fernanortega.mymoneycount.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.fernanortega.mymoneycount.presentation.navigation.MyMoneyNavHost
import com.fernanortega.mymoneycount.presentation.ui.theme.MyMoneyCountTheme
import dagger.hilt.android.AndroidEntryPoint

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
                MyMoneyNavHost(
                    modifier = Modifier
                        .fillMaxSize(),
                    appState = appState
                )
            }
        }
    }
}