package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

 const val API_KEY = "9d19b3bb33df41f390003e39aee738c1"

const val BASE_URL = "https://newsapi.org/v2/"


interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String, // REQUIRED - cannot be empty
        @Query("sources") sources: String? = null,
        @Query("domains") domains: String? = null,
        @Query("language") language: String = "en",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("pageSize") pageSize: Int = 15,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}

