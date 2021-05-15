package com.tushar.newsapp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.tushar.newsapp.R
import com.tushar.newsapp.data.ViewData
import com.tushar.newsapp.databinding.ActivityHomeBinding
import com.tushar.newsapp.view.adapter.NewsFragmentStateAdapter
import com.tushar.newsapp.viewmodel.HomeActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    private val homeActivityViewModel: HomeActivityViewModel by viewModels()
    private val TAG = "HomeActivity"

    //Tab Texts
    private val newsTabTexts = arrayOf("News Org", "Google Books")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initViews()
    }

    //Initializing views
    private fun initViews() {
        val newsFragmentAdapter = NewsFragmentStateAdapter(this)
        binding.newsPager.adapter = newsFragmentAdapter
        val tabLayout = binding.tabLayout
        //Attaching tablayout with newsViewPager
        TabLayoutMediator(tabLayout, binding.newsPager) { tab, position ->
            //Displaying tab texts
            tab.text = newsTabTexts[position]
            binding.newsPager.setCurrentItem(tab.position, true)
        }.attach()

        //Fetching view data
        homeActivityViewModel.fetchViewData()
        homeActivityViewModel.viewData.observe(this, Observer {
            it.viewCount
            Log.d(TAG, "initViews:${it.viewCount}")
            updateViewCount(it.viewCount + 1)
        })
    }

    //updateViewCount()
    fun updateViewCount(count: Int) {
        homeActivityViewModel.updateViewData(ViewData(count))


    }
}