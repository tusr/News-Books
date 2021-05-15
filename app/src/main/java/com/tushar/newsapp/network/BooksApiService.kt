package com.tushar.newsapp.network


import com.tushar.newsapp.network.responses.gbooks.GoogleBooks
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface BooksApiService {


    @GET("volumes?q=search-terms&key=AIzaSyCcJ0KRGAj7pmoQ6PKPYv1SWMYDCP5_-JY")
    suspend fun getBooks(): Response<GoogleBooks>


    companion object {
        private val BASE_URL = "https://www.googleapis.com/books/v1/"

        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): BooksApiService {
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
                .build().create(BooksApiService::class.java)
        }
    }
}