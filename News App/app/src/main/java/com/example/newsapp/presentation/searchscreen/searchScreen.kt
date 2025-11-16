package com.example.newsapp.presentation.searchscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.utils.Dimens._12Dp
import com.example.newsapp.utils.Dimens._16Dp
import com.example.newsapp.presentation.homescreen.component.Articles
import com.example.newsapp.presentation.homescreen.component.common.SearchBarSection
import kotlinx.coroutines.flow.Flow


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val searchViewModel: SearchScreenViewModel = hiltViewModel()


    val focusRequester = remember {
        FocusRequester()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    val query = remember {
        mutableStateOf("")
    }

    val listOfNews = remember {
        mutableStateOf<Flow<PagingData<Article>>?>(null)
    }


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
                query.value = it
            },
            onSearch = {
                listOfNews.value = searchViewModel.getNewsforSearch(query.value)
            }
        )

        listOfNews.value?.let {
            Articles(
                news = it.collectAsLazyPagingItems(),
                navController = navController
            )
        }


    }

}

