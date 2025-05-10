package com.example.stm_assessment.Models

data class Author(
    val name: String,
    val profileImage: String
)

data class Article(
    val id: Int,
    val title: String,
    val summary: String,
    val content: String,
    val author: Author,
    val publishedAt: String,
    val category: String,
    val imageUrl: String,
    val likes: Int,
    val commentsCount: Int
)

data class NewsFeedResponse(
    val newsFeed: List<Article>
)
