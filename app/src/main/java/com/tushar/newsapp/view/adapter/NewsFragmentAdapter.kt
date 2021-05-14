package com.tushar.newsapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tushar.newsapp.view.GoogleNewsFragment
import com.tushar.newsapp.view.NewsOrgFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
class NewsFragmentAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> return NewsOrgFragment()
            1 -> return GoogleNewsFragment()
            else -> NewsOrgFragment()
        }

    }
}