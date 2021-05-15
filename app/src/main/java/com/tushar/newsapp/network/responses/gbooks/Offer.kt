package com.tushar.newsapp.network.responses.gbooks

data class Offer(
    val finskyOfferType: Int,
    val listPrice: ListPriceX,
    val retailPrice: RetailPrice
)