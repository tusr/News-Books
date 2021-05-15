package com.tushar.newsapp.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tushar.newsapp.R
import com.tushar.newsapp.network.responses.newsorg.Article
import com.tushar.newsapp.view.listeners.NewsOrgClickListener
import com.tushar.newsapp.view.viewholders.NewsOrgViewHolder


class NewsOrgListAdapter : RecyclerView.Adapter<NewsOrgViewHolder>() {

    var headLineItems = ArrayList<Article>()
    private lateinit var newsClickListener: NewsOrgClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsOrgViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_org_card, parent, false)
        return NewsOrgViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsOrgViewHolder, position: Int) {
        holder!!.onBind(headLineItems[position], newsClickListener)
    }

    override fun getItemCount(): Int {
        return headLineItems.size
    }

    fun addNews(clickListener: NewsOrgClickListener, list: ArrayList<Article>) {
        this.newsClickListener = clickListener
        headLineItems = list
        notifyDataSetChanged()

    }

}