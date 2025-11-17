package com.example.newsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class ArticleUi(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title: String,
    val content: String,
    val url: String,
    val urlToImage: String,
    val source: String,
    val publishedAt: String
)
