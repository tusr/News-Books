package com.tushar.newsapp.di

import android.content.Context
import com.tushar.newsapp.network.NetworkConnectionInterceptor
import com.tushar.newsapp.repository.FirebaseRepository
import com.tushar.newsapp.view.adapter.GoogleBooksAdapter
import com.tushar.newsapp.view.adapter.NewsFragmentStateAdapter
import com.tushar.newsapp.view.adapter.NewsOrgListAdapter


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideNetworkInterceptor(@ApplicationContext context: Context) =
        NetworkConnectionInterceptor(context)

    @Provides
    fun provideFirebaseRepository() = FirebaseRepository()

    @Provides
    fun provideNewsOrgAdapter(): NewsOrgListAdapter = NewsOrgListAdapter()

    @Provides
    fun provideGoogleBooksAdapter(): GoogleBooksAdapter = GoogleBooksAdapter()

}