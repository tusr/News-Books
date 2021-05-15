package com.tushar.newsapp.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.tushar.newsapp.data.ViewData


class FirebaseRepository() {
    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()

    // update view count
    fun updateViewCount(viewData: ViewData): Task<Void> {
        //var
        var documentReference = firestoreDB.collection("app_views").document("view")
//            .collection("saved_addresses").document("")
        return documentReference.set(viewData)
    }

    // get viewdata
    fun getViewData(): DocumentReference {
        var documentReference = firestoreDB.collection("app_views").document("view")
        return documentReference
    }
}