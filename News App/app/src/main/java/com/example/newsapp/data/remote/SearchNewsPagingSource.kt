package com.example.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remote.dto.Article

class SearchNewsPagingSource (
    private val newsApi: NewsApi,
    private val query: String,
    private val sources: String
): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1


        return try {
            val response = newsApi.searchNews(
                query = query,
                sources = sources,
                page = page)

            val articles = response.articles.distinctBy { it.title }

            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (articles.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}