package com.example.newsapp.presentation.news_navigator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.presentation.articledetailsscreen.ArticleDetailsScreen
import com.example.newsapp.presentation.bookmarksscreen.BookmarksScreen
import com.example.newsapp.presentation.homescreen.HomeScreen
import com.example.newsapp.presentation.news_navigator.component.BottomNavigationBar
import com.example.newsapp.presentation.navgraph.Route
import com.example.newsapp.presentation.searchscreen.SearchScreen
import com.example.newsapp.utils.Constants.bottomNavigationList

@Composable
fun NewsNavigator() {

    val navController = rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    Scaffold (
        bottomBar = {
            if (isBottomBarVisible) {
                BottomNavigationBar(
                    bottomNavigationItem = bottomNavigationList,
                    navHostController = navController
                )
            }
        }

    ) { innerPadding ->

        val bottomPadding = innerPadding.calculateBottomPadding()

        NavHost(
            startDestination = Route.HomeScreen.route,
            navController = navController,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                HomeScreen(navController = navController)
            }

            composable(route = Route.SearchScreen.route) {
                SearchScreen(navController = navController)
            }

            composable(
                route =Route.DetailsScreen.route + "?title={title}&content={content}&url={url}&urlToImage={urlToImage}",
                arguments = listOf(
                    navArgument(name = "title") { type = NavType.StringType },
                    navArgument(name = "content") { type = NavType.StringType },
                    navArgument(name = "url") { type = NavType.StringType },
                    navArgument(name = "urlToImage") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title")
                val content = backStackEntry.arguments?.getString("content")
                val url = backStackEntry.arguments?.getString("url")
                val urlToImage = backStackEntry.arguments?.getString("urlToImage")
                ArticleDetailsScreen(
                    navController = navController,
                    title = title,
                    content = content,
                    url = url,
                    urlToImage = urlToImage
                )
            }

            composable(route = Route.BookmarkScreen.route) {
                BookmarksScreen(navController = navController)
            }
        }
    }

    
}