package com.example.newsapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import com.example.newsapp.presentation.navgraph.Route
import com.example.newsapp.presentation.news_navigator.component.BottomNavigationItem

object Constants {

    const val USER_SETTINGS = "user_settings"
    const val APP_ENTRY = "app_entry"


     val bottomNavigationList = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
            contentDescription = "Home Screen",
            route = Route.HomeScreen.route
        ),
        BottomNavigationItem(
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unSelectedIcon = Icons.Outlined.Search,
            contentDescription = "Search Screen",
            route = Route.SearchScreen.route
        ),
        BottomNavigationItem(
            title = "Bookmarks",
            selectedIcon = Icons.Filled.Star,
            unSelectedIcon = Icons.Outlined.Star,
            contentDescription = "Bookmarks Screen",
            route = Route.BookmarkScreen.route
        ),
    )


}