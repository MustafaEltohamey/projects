package com.example.newsapp.presentation.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.utils.Dimens._60Dp
import com.example.newsapp.presentation.homescreen.component.Articles
import com.example.newsapp.presentation.homescreen.component.common.SearchBarSection
import com.example.newsapp.presentation.homescreen.component.TopImage
import com.example.newsapp.presentation.navgraph.Route


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val homeViewModel: HomeScreenViewModel = hiltViewModel()


    Column (
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {

        TopImage(
            image = painterResource(id = R.drawable.ic_splash)
        )

        SearchBarSection(
            text = "",
            readOnly = true,
            onValueChange = {

            },
            onClick = {
                navController.navigate(Route.SearchScreen.route)
            },

        )

        Spacer(modifier = Modifier.height(_60Dp))

        Articles(
            news = homeViewModel.news.collectAsLazyPagingItems(),
            navController = navController
        )


    }
}
