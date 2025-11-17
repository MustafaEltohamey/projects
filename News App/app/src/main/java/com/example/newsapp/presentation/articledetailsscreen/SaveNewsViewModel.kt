package com.example.newsapp.presentation.articledetailsscreen

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
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun insertArticle(article: ArticleUi) {
        viewModelScope.launch {
            repository.insert(article)
        }
    }

    fun deleteArticle(article: ArticleUi) {
        viewModelScope.launch {
            repository.delete(article)
        }
    }

    fun isArticleSaved(url: String?): Boolean {
        return articles.value.any { it.url == url }
    }

    fun toggleSaveArticle(article: ArticleUi) {
        viewModelScope.launch {
            if (isArticleSaved(article.url)) {
                repository.delete(article)
            } else {
                repository.insert(article)
            }
        }
    }

}