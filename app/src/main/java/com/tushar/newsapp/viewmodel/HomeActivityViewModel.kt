package com.tushar.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.tushar.newsapp.data.ViewData
import com.tushar.newsapp.network.responses.gbooks.Item
import com.tushar.newsapp.repository.FirebaseRepository
import com.tushar.newsapp.repository.Repository
import com.tushar.newsapp.utils.ApiException
import com.tushar.newsapp.utils.NoInternetException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeActivityViewModel @Inject
constructor(
    private val repository: FirebaseRepository
) :
    ViewModel() {
    var viewData: MutableLiveData<ViewData>
    private val TAG = "NewsFragmentViewModel"

    init {
        viewData = MutableLiveData()
    }


    fun fetchViewData() {
        CoroutineScope(Dispatchers.Main).launch {
            repository.getViewData().get().addOnSuccessListener {

                viewData.postValue(it.toObject(ViewData::class.java))
            }


        }

    }

    fun updateViewData(data: ViewData) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.updateViewCount(data).addOnSuccessListener {
                Log.d(TAG, "updateViewData: updated")
            }
                .addOnFailureListener {
                    Log.d(TAG, "updateViewData: ${it.message}")
                }
        }


    }

}
