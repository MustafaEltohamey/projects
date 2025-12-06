package com.example.newsapp.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.data.remote.dto.Article
import kotlinx.coroutines.flow.Flow

interface GetNewsRepository {

    fun getNews (
        sources: List<String>
    ): Flow<PagingData<Article>>

    fun searchNews (
        query: String,
        sources: List<String>
    ): Flow<PagingData<Article>>

}