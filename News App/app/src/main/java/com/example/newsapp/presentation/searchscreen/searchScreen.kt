package com.example.newsapp.presentation.searchscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.utils.Dimens._12Dp
import com.example.newsapp.utils.Dimens._16Dp
import com.example.newsapp.presentation.homescreen.component.Articles
import com.example.newsapp.presentation.homescreen.component.common.SearchBarSection


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val searchViewModel: SearchScreenViewModel = hiltViewModel()


    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

   val query = searchViewModel.query.collectAsState()
    val items = searchViewModel.items.collectAsLazyPagingItems()



    Column (
        modifier = modifier
            .padding(_16Dp)
            .statusBarsPadding(),
    ) {

        SearchBarSection(
            modifier = Modifier
                .padding(bottom = _12Dp)
                .focusRequester(focusRequester),
            text = query.value,
            readOnly = false,
            onValueChange = {
               searchViewModel.search(it)
            },
            onSearch = {
                searchViewModel.search(query.value)
            }
        )

        if (query.value.text.isNotEmpty())
            Articles(
                news = items,
                navController = navController
            )


    }

}

