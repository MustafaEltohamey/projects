package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.navgraph.OnNavGraph
import com.example.newsapp.presentation.systemBars.SystemBarColor
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = {viewModel.splashCondition.value})
        }
        setContent {
            NewsAppTheme {

                val darkIcons = isSystemInDarkTheme()

                SystemBarColor(
                    statusBarColor = Color.Transparent,
                    navigationBarColor = Color.Black,
                    darkIcons = !darkIcons
                )

                val navController= rememberNavController()

                Surface (
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    OnNavGraph(
                        startDestination = viewModel.startDestination.value,
                        navController = navController
                    )
                }

            }
        }
    }
}



