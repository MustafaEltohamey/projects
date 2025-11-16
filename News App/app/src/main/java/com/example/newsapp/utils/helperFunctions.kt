package com.example.newsapp.utils

import androidx.core.text.HtmlCompat
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.domain.model.ArticleUi
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun String.toDateformat (): String {

    return try {
        val input = OffsetDateTime.parse(this)
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy - hh:mm a")
        input.format(formatter)
    } catch (e: Exception) {
        this
    }

}


fun String?.textEnhancement () : String {
    return HtmlCompat.fromHtml(this ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}


fun Article?.toUiArticle () : ArticleUi {
    return ArticleUi(
        title = this?.title ?: "No Data",
        content = this?.content.textEnhancement(),
        url = this?.url ?: "No Data",
        urlToImage = this?.urlToImage ?: "No Data",
        source = this?.source?.name ?: "No Data",
        publishedAt = this?.publishedAt?.toDateformat() ?: "No Data"
    )
}