package com.example.newsapp.presentation.onboarding.componert

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.newsapp.R
import com.example.newsapp.utils.Dimens._16Dp
import com.example.newsapp.utils.Dimens._16Sp
import com.example.newsapp.utils.Dimens._1Dp
import com.example.newsapp.utils.Dimens._6Dp
import com.example.newsapp.presentation.onboarding.pages
import com.example.newsapp.ui.theme.SelectedIndicator
import com.example.newsapp.ui.theme.UnSelectedIndicator

@Composable
fun BottomRowPage(
    pageIndex: Int,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    onFinishClick: () -> Unit
) {

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(_16Dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

        when (pageIndex) {
            0 -> {
                Box {}

                Indicator(
                    pageIndex = pageIndex,
                    selectedColor = SelectedIndicator,
                    unSelectedColor = UnSelectedIndicator
                )

                NewsButtons(image = Icons.Default.KeyboardArrowRight,
                    description = stringResource(id = R.string.arrow_right_content_description),
                    color = colorResource(id = R.color.text_medium)){
                    onRightClick()
                }

            }
            pages.size - 1 -> {

                NewsButtons(image = Icons.Default.KeyboardArrowLeft,
                    description = stringResource(id = R.string.arrow_left_content_description),
                    color = colorResource(id = R.color.text_medium)){
                    onLeftClick()
                }

                Indicator(
                    pageIndex = pageIndex,
                    selectedColor = SelectedIndicator,
                    unSelectedColor = UnSelectedIndicator
                )

                TextButton(
                    onClick = {
                        onFinishClick()
                    },
                    modifier = Modifier.padding(_6Dp),
                    border = BorderStroke(_1Dp,Color.Black),

                ) {
                    Text(
                        text = stringResource(id = R.string.finish_boarding_screens),
                        fontSize = _16Sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            else -> {

                NewsButtons(image = Icons.Default.KeyboardArrowLeft,
                    description = stringResource(id = R.string.arrow_left_content_description),
                    color = colorResource(id = R.color.display_small)){
                    onLeftClick()
                }

                Indicator(
                    pageIndex = pageIndex,
                    selectedColor = SelectedIndicator,
                    unSelectedColor = UnSelectedIndicator
                )

                NewsButtons(image = Icons.Default.KeyboardArrowRight,
                    description = stringResource(id = R.string.arrow_right_content_description),
                    color = colorResource(id = R.color.text_medium)) {
                    onRightClick()
                }

            }
        }

    }

}