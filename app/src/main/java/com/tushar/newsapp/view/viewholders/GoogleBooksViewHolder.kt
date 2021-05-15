package com.tushar.newsapp.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tushar.newsapp.R
import com.tushar.newsapp.network.responses.gbooks.Item
import com.tushar.newsapp.view.listeners.BookClickListener
import kotlinx.android.synthetic.main.news_org_card.view.*

class GoogleBooksViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(bookData: Item, bookClickListener: BookClickListener) {
        view.tv_headline.text = bookData?.volumeInfo?.title
        view.tv_desc.text = bookData?.volumeInfo.description

        view.tv_time.text = bookData?.volumeInfo?.pageCount.toString() + " pages"
        view.tv_source.text = bookData!!.volumeInfo!!.authors?.get(0) ?: ""
        Glide.with(view).load(bookData?.volumeInfo.imageLinks.smallThumbnail)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.book)
            .into(view.iv_news)

        view.setOnClickListener {
            bookClickListener.cardClicked(bookData)
        }

    }
}