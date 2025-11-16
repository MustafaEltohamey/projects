package com.example.newsapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.domain.repository.GetNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsRepositoryIml @Inject constructor(
    private val newsApi: NewsApi
) : GetNewsRepository {

    override fun getNews(
        query: String,
        sources: List<String>?,
        domains: List<String>?)
    : Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(pageSize = 15),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi,
                    query = query,
                    sources = sources?.joinToString(","),
                    domains = domains?.joinToString(","))
            }
        ).flow
    }
}