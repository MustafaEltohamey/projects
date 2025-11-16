package com.example.newsapp.presentation.news_navigator.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
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
     navHostController: NavHostController
) {
    val isSelected = rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar (
        containerColor =  MaterialTheme.colorScheme.surface,
    ){


        bottomNavigationItem.forEachIndexed{ index, item ->
            NavigationBarItem(
                selected = isSelected.intValue == index,
                onClick = {
                    isSelected.intValue = index
                    navHostController.navigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected.intValue == index) item.selectedIcon
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