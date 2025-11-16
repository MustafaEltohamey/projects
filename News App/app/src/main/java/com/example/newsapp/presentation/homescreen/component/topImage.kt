package com.example.newsapp.presentation.homescreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.example.newsapp.R
import com.example.newsapp.utils.Dimens._120Dp
import com.example.newsapp.utils.Dimens._12Dp
import com.example.newsapp.utils.Dimens._150Dp
import com.example.newsapp.utils.Dimens._24Dp
import com.example.newsapp.utils.Dimens._32Dp
import com.example.newsapp.utils.Dimens._42Dp
import com.example.newsapp.utils.Dimens._60Dp


@Composable
fun TopImage(
    image: Painter,
    modifier: Modifier = Modifier
) {

    Image(
        modifier = modifier
            .size(width = _120Dp, height = _60Dp),
        painter = image,
        contentDescription = stringResource(id = R.string.top_image_section),
        contentScale = ContentScale.Crop,
    )

}