package com.tushar.newsapp.view.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tushar.newsapp.R
import com.tushar.newsapp.network.responses.gbooks.Item
import com.tushar.newsapp.view.listeners.BookClickListener
import com.tushar.newsapp.view.viewholders.GoogleBooksViewHolder


class GoogleBooksAdapter : RecyclerView.Adapter<GoogleBooksViewHolder>() {
    private  val TAG = "GoogleBooksAdapter"
    var booksItem = ArrayList<Item>()
    private lateinit var newsClickListener: BookClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoogleBooksViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_org_card, parent, false)
        return GoogleBooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoogleBooksViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: testing ${position}")
        holder!!.onBind(booksItem[position], newsClickListener)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ${booksItem.size}")
        return booksItem.size
    }

    fun addBooks(clickListener: BookClickListener, list: ArrayList<Item>) {
        Log.d(TAG, "addBooks: ${list.size}")
        this.newsClickListener = clickListener
        booksItem = list
        notifyDataSetChanged()

    }


}