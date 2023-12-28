package com.hanaahany.task.remote.services

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Services {
    @GET("repositories")
    suspend fun getAllRepo(): Response<AllRepoResponse>

    @GET("https://api.github.com/repos/{login}/{name}")
    suspend fun getRepo( @Path("login") login: String,
                         @Path("name") name: String): Response<RepoDetailsResponse>
}