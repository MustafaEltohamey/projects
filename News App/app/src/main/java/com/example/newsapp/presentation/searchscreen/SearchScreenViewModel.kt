package com.example.newsapp.presentation.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.domain.repository.GetNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repo: GetNewsRepository
) : ViewModel() {

    fun getNewsforSearch(query: String) : Flow<PagingData<Article>> {
        return repo.getNews(
            query = query,
            sources =  listOf(
                "abc-news",
                "al-jazeera-english",
                "associated-press",
                "the-verge",
                "wired",
                "techcrunch",
                "cnn",
                "fox-news",
                "google-news",
                "reuters",
                "time",
                "the-washington-post",
                "independent",
                "the-wall-street-journal",
                "engadget",
                "bloomberg",
            ) ,
            domains = null
        ).cachedIn(viewModelScope)


    }
}