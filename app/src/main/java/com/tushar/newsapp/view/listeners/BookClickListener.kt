package com.tushar.newsapp.view.listeners

import com.tushar.newsapp.network.responses.gbooks.Item

interface BookClickListener {
    fun cardClicked(article: Item)
}