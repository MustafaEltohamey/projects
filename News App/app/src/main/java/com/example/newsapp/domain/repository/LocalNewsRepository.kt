package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.ArticleUi
import kotlinx.coroutines.flow.Flow

interface LocalNewsRepository {

    fun getArticles(): Flow<List<ArticleUi>>

    suspend fun insert(article: ArticleUi)

    suspend fun delete(article: ArticleUi)

}