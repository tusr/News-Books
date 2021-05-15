package com.tushar.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tushar.newsapp.network.responses.gbooks.Item
import com.tushar.newsapp.repository.Repository
import com.tushar.newsapp.utils.ApiException
import com.tushar.newsapp.utils.NoInternetException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleBooksViewModel @Inject
constructor(
    private val repository: Repository
) :
    ViewModel() {
    var booksListLiveData: MutableLiveData<ArrayList<Item>>

    //    var booksListLiveData: MutableLiveData<GoogleBooks>
    private val TAG = "NewsFragmentViewModel"

    init {
        booksListLiveData = MutableLiveData()
    }

    fun getBooksObserver(): MutableLiveData<ArrayList<Item>> {

        return booksListLiveData
    }

    fun fetchBooks() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val bookData =
                    repository.fetchGoogleBooks()
                booksListLiveData.postValue(ArrayList(bookData.items))
                Log.d(TAG, "fetchHeadLineData: try")
            } catch (e: ApiException) {
                Log.d(TAG, "fetchHeadLineData: Api exception")
            } catch (e: NoInternetException) {
                Log.d(TAG, "fetchHeadLineData: No Network")
            }
        }

    }

}
