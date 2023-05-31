package com.ales.fittrackmobile.api

import com.ales.fittrackmobile.context.UserContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient

class HttpClient {

    val client = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val originalRequest = chain.request()
            val token = UserContext.getInstance().token
            var newRequest = originalRequest

            if (token.isNotEmpty()) {
                newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            }


            chain.proceed(newRequest)
        })
        .build()


}