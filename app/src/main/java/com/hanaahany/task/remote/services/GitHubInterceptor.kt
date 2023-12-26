package com.hanaahany.task.remote.services

import com.facebook.shimmer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class GitHubInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        val modifiedRequest: Request = originalRequest.newBuilder()
            .build()
        return chain.proceed(modifiedRequest)
    }
}