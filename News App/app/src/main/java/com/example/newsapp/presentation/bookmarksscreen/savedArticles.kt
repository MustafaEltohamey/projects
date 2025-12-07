package com.example.newsapp.presentation.bookmarksscreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newsapp.domain.model.ArticleUi
import com.example.newsapp.presentation.homescreen.component.ArticleCard

@Composable
fun SavedArticles(
    articles: List<ArticleUi>,
    navController: NavHostController
) {

   LazyColumn (
       contentPadding = PaddingValues(
           vertical = 8.dp,
           horizontal = 8.dp
       )
   )  {

       items(articles.size) {
           ArticleCard(
               navController = navController,
               article = articles[it]
           )
       }

   }

}