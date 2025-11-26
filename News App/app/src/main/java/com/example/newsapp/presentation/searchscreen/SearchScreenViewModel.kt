package com.example.newsapp.presentation.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.domain.repository.GetNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repo: GetNewsRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query

    @OptIn(ExperimentalCoroutinesApi::class)
    val items = _query.flatMapLatest { q ->
        if (q.isEmpty())
            flowOf(PagingData.empty())
        else {
            repo.getNews(
                query = q,
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
            )
        }
    }.cachedIn(viewModelScope)



    fun search(q: String) {
        _query.value = q
    }
}