@file:OptIn(ExperimentalFoundationApi::class)

package com.example.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.newsapp.presentation.onboarding.componert.BottomRowPage
import com.example.newsapp.presentation.onboarding.componert.OnBoardingEvent
import com.example.newsapp.presentation.onboarding.componert.TopBoardingSection
import kotlinx.coroutines.launch


@Composable
fun OnBoardingPage(
    onEvent: (OnBoardingEvent) -> Unit
) {

    val pagerState = rememberPagerState(pageCount = {
        pages.size
    })


    val scope = rememberCoroutineScope()

    HorizontalPager(state = pagerState) { page ->
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            TopBoardingSection(page = pages[page])
            BottomRowPage(pageIndex = page,
                onLeftClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(page - 1)
                    }
                }, onRightClick =  {
                    scope.launch {
                        pagerState.animateScrollToPage(page + 1)
                    }
                }, onFinishClick = {
                    onEvent(OnBoardingEvent.FinishClicked)
                }
            )
        }

    }
}

