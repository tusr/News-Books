package com.tushar.newsapp.view.googlebooks

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
import com.tushar.newsapp.databinding.DialogBookBinding
import com.tushar.newsapp.databinding.FragmentGoogleBooksBinding
import com.tushar.newsapp.network.responses.gbooks.Item
import com.tushar.newsapp.repository.Repository
import com.tushar.newsapp.view.adapter.GoogleBooksAdapter
import com.tushar.newsapp.view.listeners.BookClickListener
import com.tushar.newsapp.viewmodel.GoogleBooksViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GoogleNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class GoogleBooksFragment : Fragment(), BookClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentGoogleBooksBinding
    private val booksViewModel: GoogleBooksViewModel by viewModels()

    @Inject
    lateinit var booksAdapter: GoogleBooksAdapter
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    private lateinit var customAlertDialogView: View
    private lateinit var bookBinding: DialogBookBinding

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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_google_books, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        materialAlertDialogBuilder =
            MaterialAlertDialogBuilder(requireContext())

        //Setting item animator and adapter for recyclerview
        binding.rvBooks.itemAnimator = DefaultItemAnimator()
        binding.rvBooks.adapter = booksAdapter
        binding.rvBooks.layoutManager = LinearLayoutManager(requireContext())
        booksViewModel.fetchBooks()
//
        booksViewModel.getBooksObserver().observe(requireActivity(),
            Observer {
                binding.lottieLoader.visibility = View.INVISIBLE
                booksAdapter.addBooks(this, it)
            })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GoogleNewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GoogleBooksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun cardClicked(article: Item) {
        displayDialog(article)

    }

    private fun displayDialog(article: Item) {
        customAlertDialogView = LayoutInflater.from(requireActivity())
            .inflate(R.layout.news_org_card, null, false)

        bookBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.dialog_book,
            null,
            false
        )

        bookBinding.viewmodel = article

        materialAlertDialogBuilder.setView(bookBinding.root)
            .show()
    }
}