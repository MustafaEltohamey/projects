package com.example.newsapp.presentation.news_navigator.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val contentDescription: String,
    val route: String
)



@Composable
fun BottomNavigationBar(
     bottomNavigationItem: List<BottomNavigationItem>,
     navHostController: NavHostController,
     currentDestination: NavDestination?,
) {

    //val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    //val currentDestination = navBackStackEntry?.destination

    NavigationBar (
        containerColor =  MaterialTheme.colorScheme.surface,
    ){


        bottomNavigationItem.forEach{ item ->
            val selected = item.route == currentDestination?.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navHostController.navigate(item.route) {
                        popUpTo(navHostController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon
                            else item.unSelectedIcon,
                        contentDescription = item.contentDescription,
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

            )


        }
    }

}