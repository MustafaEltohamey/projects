package com.example.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)


val pages = listOf(
    Page(
        title = "Stay Updated, Anytime",
        description = "Get the latest news from trusted sources right at your fingertips, wherever you are.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "News That Matters",
        description = "Follow topics you care about and personalize your feed to match your interests.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Fast & Reliable",
        description = "Enjoy breaking news alerts and in-depth articles, all in one easy-to-use app.",
        image = R.drawable.onboarding3
    )
)