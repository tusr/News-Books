package com.tushar.newsapp.view.listeners

import com.tushar.newsapp.network.responses.newsorg.Article

interface NewsOrgClickListener {
    fun cardClicked(article: Article)
}