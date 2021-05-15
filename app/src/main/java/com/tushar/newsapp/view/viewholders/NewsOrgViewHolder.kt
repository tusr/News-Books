package com.tushar.newsapp.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tushar.newsapp.network.responses.newsorg.Article
import com.tushar.newsapp.utils.Utils
import com.tushar.newsapp.view.listeners.NewsOrgClickListener
import kotlinx.android.synthetic.main.news_org_card.view.*

class NewsOrgViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(articleData: Article, newsClickListener: NewsOrgClickListener) {
        view.tv_headline.text = articleData.title
        view.tv_desc.text = articleData.description

        view.tv_time.text = Utils.getFormattedDate(articleData.publishedAt!!)
        view.tv_source.text = articleData.source.name
        Glide.with(view).load(articleData.urlToImage)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(view.iv_news)

        view.setOnClickListener {
            newsClickListener.cardClicked(articleData)
        }

    }
}