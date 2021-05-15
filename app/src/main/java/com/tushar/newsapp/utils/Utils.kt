package com.tushar.newsapp.utils

import java.text.SimpleDateFormat

class Utils {
    companion object {

        val NEWS_URL = "news_url"
        val SOURCE_NAME = "source_name"
        val IMAGE_URL = "image_url"
        val AUTHOR_NAME = "author_name"
        val CONTENT = "content"
        val DESCRIPTION = "description"
        val PUBLISHED_AT = "published_at"
        val TITLE = "title"


        fun getFormattedDate(s: String): String {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("dd.MM.yyyy")
            val output = formatter.format(parser.parse(s))
            return output
        }
    }
}