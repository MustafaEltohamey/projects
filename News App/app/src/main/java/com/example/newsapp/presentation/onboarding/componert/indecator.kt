package com.example.newsapp.presentation.onboarding.componert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.newsapp.utils.Dimens._14Dp
import com.example.newsapp.utils.Dimens._3Dp
import com.example.newsapp.presentation.onboarding.pages

@Composable
fun Indicator(
    modifier: Modifier = Modifier,
    pageIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color
) {

    Row (modifier = modifier) {
        repeat(pages.size) {
            Box(modifier = Modifier
                .padding(_3Dp)
                .size(_14Dp)
                .clip(CircleShape)
                .background(if (pageIndex == it) selectedColor else unSelectedColor)
            )
        }
    }

}