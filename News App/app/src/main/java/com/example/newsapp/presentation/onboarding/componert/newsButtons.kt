package com.example.newsapp.presentation.onboarding.componert

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newsapp.utils.Dimens._40Dp

@Composable
fun NewsButtons(
    modifier: Modifier = Modifier,
    image: ImageVector,
    description: String,
    color: Color,
    onIndicatorClick: () -> Unit
) {

    Box(modifier = modifier
        .size(_40Dp)
        .clip(CircleShape)
        .clickable {
             onIndicatorClick()
        },
        contentAlignment = Alignment.Center
    ) {
        Image(imageVector = image,
            contentDescription = description,
            colorFilter = ColorFilter.tint(color)
        )
    }

}