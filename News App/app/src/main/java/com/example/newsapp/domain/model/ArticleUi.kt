package com.example.newsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class ArticleUi(
    val title: String,
    val content: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String,
    val source: String,
    val publishedAt: String
)
