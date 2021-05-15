package com.tushar.newsapp.network.responses.newsorg


data class NewsOrgNews(
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
)