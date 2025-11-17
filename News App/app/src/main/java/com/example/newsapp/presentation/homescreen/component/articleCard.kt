package com.example.newsapp.presentation.homescreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.example.newsapp.R
import com.example.newsapp.presentation.navgraph.Route
import com.example.newsapp.utils.Dimens._10Sp
import com.example.newsapp.utils.Dimens._120Dp
import com.example.newsapp.utils.Dimens._12Dp
import android.net.Uri
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.newsapp.domain.model.ArticleUi
import com.example.newsapp.utils.Dimens._16Dp
import com.example.newsapp.utils.Dimens._18Dp
import com.example.newsapp.utils.Dimens._3Dp
import com.example.newsapp.utils.Dimens._42Dp
import com.example.newsapp.utils.Dimens._6Dp
import com.example.newsapp.utils.Dimens._96Dp
import com.example.newsapp.utils.toDateformat

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    article: ArticleUi
) {

    Row (
        modifier = modifier
            .padding(horizontal = _16Dp, vertical = _16Dp)
            .fillMaxWidth()
            .height(_120Dp)
            .clickable {
                val title = Uri.encode(article.title)
                val content = Uri.encode(article.content)
                val url = Uri.encode(article.url)
                val urlToImage = Uri.encode(article.urlToImage)
                val source = Uri.encode(article.source)
                val publishedAt = Uri.encode(article.publishedAt)
                navController.navigate(
                    route = "${Route.DetailsScreen.route}?title=$title&content=$content&url=$url&urlToImage=$urlToImage&source=$source&publishedAt=$publishedAt"
                )
            },
        verticalAlignment = Alignment.CenterVertically,

    ){

        AsyncImage(
            modifier = Modifier
                .padding(end = _6Dp)
                .size(_96Dp)
                .clip(RoundedCornerShape(_12Dp)),
            model = article.urlToImage,
            contentDescription = stringResource(R.string.description_picture_of_the_article),
            contentScale = ContentScale.Crop
        )

        Column {

            Text(
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(_42Dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = _10Sp,
                    color = colorResource(id = R.color.text_medium)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = stringResource(R.string.description_clock_icon),
                    tint = colorResource(id = R.color.text_medium),
                    modifier = Modifier
                        .padding(horizontal = _3Dp)
                        .size(_18Dp)
                )

                Text(
                    text = article.publishedAt.toDateformat(),
                    fontSize = _10Sp,
                    color = colorResource(id = R.color.text_medium)

                )
            }

        }
    }

}

