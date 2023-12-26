package com.hanaahany.task.remote.services



import com.hanaahany.task.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private val client = OkHttpClient.Builder().addInterceptor(GitHubInterceptor()).build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofitService: Services by lazy {
        retrofit.create(Services::class.java)
    }
}