package com.example.newsapp.presentation.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.domain.repository.GetNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor (
    private val repo : GetNewsRepository
): ViewModel() {


    val news = repo.getNews(
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
        )
    ).cachedIn(viewModelScope)
}