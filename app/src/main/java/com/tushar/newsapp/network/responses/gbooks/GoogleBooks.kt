package com.tushar.newsapp.network.responses.gbooks

data class GoogleBooks(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)