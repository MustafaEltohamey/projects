package com.example.newsapp.presentation.homescreen.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.utils.toUiArticle

@Composable
fun Articles(
    news: LazyPagingItems<Article>,
    navController: NavHostController
) {
    LazyColumn (contentPadding = PaddingValues(
        vertical = 8.dp,
        horizontal = 8.dp
    )
    ){
        items(news.itemCount) {
            ArticleCard(
                navController = navController,
                article = news[it].toUiArticle()
            )
        }

    }
}