package com.hanaahany.task.remote.services

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import retrofit2.Response
import retrofit2.http.GET

interface Services {
    @GET("repositories")
    suspend fun getAllRepo(): Response<AllRepoResponse>
}