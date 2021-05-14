package com.tushar.newsapp.di

import com.tushar.newsapp.view.GoogleNewsFragment
import com.tushar.newsapp.view.NewsOrgFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideNewsOrgFragment(): NewsOrgFragment = NewsOrgFragment()

    @Provides
    fun provideGoogleNewsFragment(): GoogleNewsFragment = GoogleNewsFragment()
}