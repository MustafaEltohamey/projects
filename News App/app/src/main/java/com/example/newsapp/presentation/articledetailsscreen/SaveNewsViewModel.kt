package com.example.newsapp.presentation.articledetailsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.ArticleUi
import com.example.newsapp.domain.repository.LocalNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveNewsViewModel @Inject constructor(
    private val repository: LocalNewsRepository
): ViewModel() {

    val articles = repository.getArticles().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )

//    fun isArticleSaved(url: String?): Boolean {
//        return articles.value.any { it.url == url }
//    }

    fun toggleSaveArticle(article: ArticleUi) {
        viewModelScope.launch {
            val cleanUrl = article.url.trim()

            val existingArticle = articles.value.find {
                it.url.trim() == cleanUrl
            }

            Log.d("SaveNewsViewModel", "Looking for URL: $cleanUrl")
            Log.d("SaveNewsViewModel", "Saved articles count: ${articles.value.size}")
            Log.d("SaveNewsViewModel", "Existing article found: ${existingArticle != null}")

            if (existingArticle != null) {
                repository.delete(article)
                Log.d("SaveNewsViewModel", "Article deleted: ${article.title}")
            } else {
                repository.insert(article)
                Log.d("SaveNewsViewModel", "Article inserted: ${article.title}")
            }
        }
    }

}