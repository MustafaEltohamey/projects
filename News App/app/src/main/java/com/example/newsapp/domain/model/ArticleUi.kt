package com.example.newsapp.domain.model

import androidx.room.Entity

@Entity(tableName = "news")
data class ArticleUi(
    val title: String,
    val content: String,
    val url: String,
    val urlToImage: String,
    val source: String,
    val publishedAt: String
)
