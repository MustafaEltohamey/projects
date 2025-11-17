package com.example.newsapp.data.remote.repository

import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.domain.model.ArticleUi
import com.example.newsapp.domain.repository.LocalNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalNewsRepositoryImp @Inject constructor(
    private val dao: NewsDao
) : LocalNewsRepository {
    override fun getArticles(): Flow<List<ArticleUi>> {
        return dao.getArticles()
    }

    override suspend fun insert(article: ArticleUi) {
        dao.insert(article)
    }

    override suspend fun delete(article: ArticleUi) {
        dao.delete(article)
    }

}