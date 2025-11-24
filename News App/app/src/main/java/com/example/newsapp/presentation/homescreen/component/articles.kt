package com.example.newsapp.presentation.homescreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.presentation.common.ArticleCardShimmerEffect
import com.example.newsapp.utils.Dimens._24Dp
import com.example.newsapp.utils.Dimens._8Dp
import com.example.newsapp.utils.toUiArticle

@Composable
fun Articles(
    news: LazyPagingItems<Article>,
    navController: NavHostController
) {

    val handlePagingResults = handlePagingResults(news = news)

    if (handlePagingResults) {
        LazyColumn (contentPadding = PaddingValues(
            vertical = _8Dp,
            horizontal = _8Dp
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
}

@Composable
fun handlePagingResults(news: LazyPagingItems<Article>): Boolean {

    val loadState = news.loadState

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null -> {
            ShimmerEffect()
            false
        }
        else -> {
            true
        }
    }
}


@Composable
fun ShimmerEffect() {
    Column (
        verticalArrangement = Arrangement.spacedBy(_24Dp)
    ) {
        repeat(10) {
            ArticleCardShimmerEffect()
        }
    }
    
}