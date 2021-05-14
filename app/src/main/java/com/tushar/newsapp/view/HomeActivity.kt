package com.tushar.newsapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.tushar.newsapp.R
import com.tushar.newsapp.databinding.ActivityHomeBinding
import com.tushar.newsapp.view.adapter.NewsFragmentAdapter


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    //Tab Texts
    private val newsTabTexts = arrayOf("News Org", "Google News")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initViews()
    }

    //Initializing views
    private fun initViews() {
        val newsFragmentAdapter = NewsFragmentAdapter(this)
        binding.newsPager.adapter = newsFragmentAdapter
        val tabLayout = binding.tabLayout

        //Attaching tablayout with newsViewPager
        TabLayoutMediator(tabLayout, binding.newsPager) { tab, position ->
            //Displaying tab texts 
            tab.text = newsTabTexts[position]
            binding.newsPager.setCurrentItem(tab.position, true)
        }.attach()
    }
}