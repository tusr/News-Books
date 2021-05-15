package com.tushar.newsapp.network


import com.tushar.newsapp.network.responses.newsorg.NewsOrgNews
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface NewsApiService {


    @GET("top-headlines?country=us&apiKey=41b584081c0f4aedb17c361efee0520b")
    suspend fun getTopHeadLines(): Response<NewsOrgNews>


    companion object {
        private val BASE_URL = "https://newsapi.org/v2/"

        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): NewsApiService {
            // Logging interceptor for intercepting network calls
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(30L, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(NewsApiService::class.java)
        }
    }
}