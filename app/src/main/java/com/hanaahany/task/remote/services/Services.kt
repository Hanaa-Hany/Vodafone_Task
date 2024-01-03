package com.hanaahany.task.remote.services

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.issues.IssuesResponse
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import com.hanaahany.task.model.response.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {
    @GET("repositories")
    suspend fun getAllRepo(): Response<AllRepoResponse>

    @GET("repos/{owner}/{repo}")
    suspend fun getRepo(@Path("owner") owner:String,
                         @Path("repo") repo: String): Response<RepoDetailsResponse>

    @GET("repos/{owner}/{repo}/issues")
    suspend fun getIssues(@Path("owner") owner:String,
                        @Path("repo") repo: String): Response<IssuesResponse>

    @GET("search/repositories")
    suspend fun searchRepo(@Query("q")query:String): Response<SearchResponse>
}