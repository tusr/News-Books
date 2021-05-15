package com.tushar.newsapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tushar.newsapp.view.googlebooks.GoogleBooksFragment
import com.tushar.newsapp.view.newsorg.NewsOrgListFragment


class NewsFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> return NewsOrgListFragment()
            1 -> return GoogleBooksFragment()
            else -> NewsOrgListFragment()
        }

    }
}