package com.tushar.newsapp.repository

import com.tushar.newsapp.network.BooksApiService
import com.tushar.newsapp.network.NetworkConnectionInterceptor
import com.tushar.newsapp.network.NewsApiService
import com.tushar.newsapp.network.SafeApiRequest
import com.tushar.newsapp.network.responses.gbooks.GoogleBooks
import com.tushar.newsapp.network.responses.gbooks.Item
import com.tushar.newsapp.network.responses.newsorg.Article
import javax.inject.Inject

class Repository @Inject constructor(

    private val networkConnectionInterceptor: NetworkConnectionInterceptor
) : SafeApiRequest() {
    private val TAG = "NewsRepository"
    suspend fun fetchHeadLine(): ArrayList<Article> {
        val headLineResponse =
            apiRequest { NewsApiService(networkConnectionInterceptor).getTopHeadLines() }
        return headLineResponse.articles
    }

    suspend fun fetchGoogleBooks(): GoogleBooks {
        return apiRequest {
            BooksApiService(networkConnectionInterceptor).getBooks()
        }
    }
}