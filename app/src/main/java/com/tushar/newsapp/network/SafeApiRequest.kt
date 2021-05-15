package com.tushar.newsapp.network

import com.tushar.newsapp.utils.ApiException
import org.json.JSONException
import retrofit2.Response


abstract class SafeApiRequest {
    //Generic function to perform api request
    suspend fun <T : Any> apiRequest(
        call: suspend () ->
        Response<T>
    ): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val message = StringBuilder()
            val error = response.errorBody()?.string()
            error?.let {
                try {
                    message.append(error)
                } catch (e: JSONException) {

                }
            }
            throw ApiException(message.toString())
        }
    }
}