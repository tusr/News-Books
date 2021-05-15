package com.tushar.newsapp.view.newsorg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tushar.newsapp.R
import com.tushar.newsapp.databinding.DialogNewsBinding
import com.tushar.newsapp.databinding.FragmentNewsBinding
import com.tushar.newsapp.network.responses.newsorg.Article
import com.tushar.newsapp.repository.Repository
import com.tushar.newsapp.view.adapter.NewsOrgListAdapter
import com.tushar.newsapp.view.listeners.NetworkListener
import com.tushar.newsapp.view.listeners.NewsOrgClickListener
import com.tushar.newsapp.viewmodel.NewsOrgViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class NewsOrgListFragment : Fragment(), NewsOrgClickListener, NetworkListener {

    private var param1: String? = null
    private var param2: String? = null
    private val TAG = "NewsFragment"
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsBinding: DialogNewsBinding
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    private lateinit var customAlertDialogView: View
    private val newsViewModel: NewsOrgViewModel by viewModels()

    @Inject
    lateinit var newsAdapter: NewsOrgListAdapter

    @Inject
    lateinit var newsRepository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        materialAlertDialogBuilder =
            MaterialAlertDialogBuilder(requireContext(), R.style.CustomDialogAnimation)
        //Setting item animator and adapter for recyclerview
        binding.rvNews.itemAnimator = DefaultItemAnimator()
        binding.rvNews.adapter = newsAdapter

        //fetching Data
        newsViewModel.fetchHeadLineData()
        //setting Observer to data in viewModel
        newsViewModel.getHeadLineObserver().observe(requireActivity(), Observer {
            binding.lottieLoader.visibility = View.INVISIBLE
            newsAdapter.addNews(this, it)

        })

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsOrgListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //Handling Card click event
    override fun cardClicked(newsArticle: Article) {

        displayDialog(newsArticle)


    }

    override fun onFailed() {

    }

    override fun onSuccess() {

    }

    private fun displayDialog(article: Article) {
        customAlertDialogView = LayoutInflater.from(requireActivity())
            .inflate(R.layout.news_org_card, null, false)

        newsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.dialog_news,
            null,
            false
        )

        newsBinding.viewmodel = article

        materialAlertDialogBuilder.setView(newsBinding.root)
            .show()
    }
}