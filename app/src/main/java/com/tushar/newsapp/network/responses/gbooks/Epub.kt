package com.tushar.newsapp.network.responses.gbooks

data class Epub(
    val acsTokenLink: String,
    val downloadLink: String,
    val isAvailable: Boolean
)