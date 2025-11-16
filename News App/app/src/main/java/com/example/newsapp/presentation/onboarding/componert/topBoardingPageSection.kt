package com.example.newsapp.presentation.onboarding.componert

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.newsapp.R
import com.example.newsapp.utils.Dimens._10Dp
import com.example.newsapp.utils.Dimens._24Sp
import com.example.newsapp.utils.Dimens._60Dp
import com.example.newsapp.utils.Dimens._6Dp
import com.example.newsapp.presentation.onboarding.Page

@Composable
fun TopBoardingSection(
    page: Page,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .navigationBarsPadding()
    ) {

        Image(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f),
            painter = painterResource(page.image)
            ,contentDescription = page.description,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(_10Dp))

        Text(modifier = Modifier.padding(_6Dp),
            text = page.title,
            color = colorResource(id = R.color.display_small),
            fontSize = _24Sp,
            fontWeight = FontWeight.Bold
        )

        Text(modifier = Modifier.padding(_6Dp),
            text = page.description,
            color = colorResource(id = R.color.text_medium),
            fontSize = _24Sp
        )

        Spacer(modifier = Modifier.height(_60Dp))



    }
}