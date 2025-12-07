package com.example.newsapp.presentation.bookmarksscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsapp.R
import com.example.newsapp.presentation.articledetailsscreen.SaveNewsViewModel
import com.example.newsapp.utils.Dimens._16Dp
import com.example.newsapp.utils.Dimens._24Dp
import com.example.newsapp.utils.Dimens._24Sp


@Composable
fun BookmarksScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val savedNewsViewModel : SaveNewsViewModel = hiltViewModel()
    val articles = savedNewsViewModel.articles.collectAsState()

    Column (
        modifier = modifier
            .padding(_16Dp)
            .statusBarsPadding(),
    ) {

        Text(
            modifier = Modifier
                .padding(top = _16Dp, bottom = _24Dp, start = _16Dp),
            text = stringResource(id = R.string.bookmarksScreen_title),
            fontWeight = FontWeight.ExtraBold,
            fontSize = _24Sp
        )

        SavedArticles(
            articles = articles.value,
            navController = navController
        )
    }

}