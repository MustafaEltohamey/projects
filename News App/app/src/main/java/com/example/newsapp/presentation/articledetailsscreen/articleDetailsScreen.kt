package com.example.newsapp.presentation.articledetailsscreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.newsapp.R
import com.example.newsapp.utils.Dimens._12Dp
import com.example.newsapp.utils.Dimens._16Dp
import com.example.newsapp.utils.Dimens._190Dp
import com.example.newsapp.utils.Dimens._248Dp
import com.example.newsapp.utils.Dimens._32Dp
import com.example.newsapp.utils.Dimens._6Dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.newsapp.domain.model.ArticleUi
import com.example.newsapp.utils.textEnhancement

@Composable
fun ArticleDetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    title: String? = null,
    content: String? = null,
    url: String? = null,
    urlToImage: String? = null,
    source: String? = null,
    publishedAt: String? = null
) {

    val savedNewsViewModel : SaveNewsViewModel = hiltViewModel()
   // val savedArticles by savedNewsViewModel.articles.collectAsState()

//    val isSaved by remember(savedArticles, url) {
//        derivedStateOf { savedArticles.any { it.url == url } }
//    }

    val decodedUrl =  Uri.decode(url ?: "")
    val context = LocalContext.current
    Column (
        modifier = modifier
            .padding(_12Dp)
            .statusBarsPadding(),
    ) {

        Row (
            modifier = Modifier
                .padding(vertical = _16Dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.arrowback_desc)
            )

            Spacer(modifier = Modifier.width(_190Dp))


            Icon(
                modifier = Modifier
                    .clickable {
                        val article = ArticleUi(
                            title = Uri.decode(title ?: ""),
                            content = Uri.decode(content ?: ""),
                            url = decodedUrl,
                            urlToImage = Uri.decode(urlToImage ?: ""),
                            source = Uri.decode(source ?: ""),
                            publishedAt = Uri.decode(publishedAt ?: "")
                        )

                        savedNewsViewModel.toggleSaveArticle(article)
                    },
                imageVector =Icons.Outlined.Star,
                contentDescription = stringResource(id = R.string.star_desc)
            )

            Spacer(modifier = Modifier.width(_32Dp))


            Icon(
                modifier = Modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, url)
                        }
                        context.startActivity(Intent.createChooser(intent, "Share link with"))
                    },
                imageVector =Icons.Default.Share,
                contentDescription = stringResource(id = R.string.share_desc)
            )

            Spacer(modifier = Modifier.width(_32Dp))

            Icon(
                modifier = Modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, url?.toUri())
                        context.startActivity(intent)
                    },
                imageVector = Icons.Default.ExitToApp,
                contentDescription = stringResource(id = R.string.earth_desc)
            )

        }


        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(_248Dp)
                .padding(vertical = _16Dp, horizontal = _12Dp)
                .clip(RoundedCornerShape(_12Dp)),
            model = ImageRequest.Builder(context)
                .data(data = urlToImage)
                .crossfade(true)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = stringResource(id = R.string.description_picture_of_the_article),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )

        Text(
            text = title ?: "No Data" ,
            fontWeight = FontWeight.Black
        )

        Text(
            modifier =  Modifier
                .padding(top = _6Dp),
            text = content.textEnhancement().ifEmpty { "No Data" },
        )

    }

}
