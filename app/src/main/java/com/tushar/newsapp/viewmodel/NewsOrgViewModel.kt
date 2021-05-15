package com.tushar.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tushar.newsapp.network.responses.newsorg.Article
import com.tushar.newsapp.repository.Repository
import com.tushar.newsapp.utils.ApiException
import com.tushar.newsapp.utils.NoInternetException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsOrgViewModel @Inject
constructor(
    private val newsRepository: Repository
) :
    ViewModel() {
    var headLineListLiveData: MutableLiveData<ArrayList<Article>>
    private val TAG = "NewsFragmentViewModel"

    init {
        headLineListLiveData = MutableLiveData()
    }

    fun getHeadLineObserver(): MutableLiveData<ArrayList<Article>> {

        return headLineListLiveData
    }

    fun fetchHeadLineData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val healLineResponse = newsRepository.fetchHeadLine()
                headLineListLiveData.postValue(healLineResponse)
                Log.d(TAG, "fetchHeadLineData: try")
            } catch (e: ApiException) {
                Log.d(TAG, "fetchHeadLineData: Api exception")
            } catch (e: NoInternetException) {
                Log.d(TAG, "fetchHeadLineData: No Network")
            }
        }

    }

}
