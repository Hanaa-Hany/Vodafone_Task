package com.hanaahany.task.remote.services

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Services {
    @GET("repositories")
    suspend fun getAllRepo(): Response<AllRepoResponse>

    @GET("repos/{owner}/{repo}")
    suspend fun getRepo(@Path("owner") owner:String,
                         @Path("repo") repo: String): Response<RepoDetailsResponse>
}