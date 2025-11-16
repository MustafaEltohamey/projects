package com.example.newsapp.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.data.remote.dto.Article
import kotlinx.coroutines.flow.Flow

interface GetNewsRepository {

    fun getNews (
        query: String,
        sources: List<String>? = null,
        domains: List<String>? = null)
    : Flow<PagingData<Article>>

}