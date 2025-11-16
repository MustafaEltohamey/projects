package com.example.newsapp.presentation.systemBars

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

@Suppress("DEPRECATION")
@Composable
fun SystemBarColor(
    statusBarColor: Color,
    navigationBarColor: Color,
    darkIcons: Boolean
) {

    val view = LocalView.current
    val window = (view.context as Activity).window



    SideEffect {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        window.statusBarColor = statusBarColor.toArgb()
        window.navigationBarColor = navigationBarColor.toArgb()

        val controller = WindowInsetsControllerCompat(window, view)
        controller.isAppearanceLightStatusBars = darkIcons
        controller.isAppearanceLightNavigationBars = darkIcons
    }

}