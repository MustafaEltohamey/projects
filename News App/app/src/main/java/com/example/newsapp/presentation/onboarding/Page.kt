package com.example.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.newsapp.R

data class Page(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)


val pages = listOf(
    Page(
        title = R.string.onboarding_page1_title,
        description = R.string.onboarding_page1_description,
        image = R.drawable.onboarding1
    ),
    Page(
        title = R.string.onboarding_page2_title,
        description = R.string.onboarding_page2_description,
        image = R.drawable.onboarding2
    ),
    Page(
        title = R.string.onboarding_page3_title,
        description = R.string.onboarding_page3_description,
        image = R.drawable.onboarding3
    )
)