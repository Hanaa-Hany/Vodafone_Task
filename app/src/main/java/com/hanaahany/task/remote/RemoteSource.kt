package com.hanaahany.task.remote

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.issues.IssuesResponse
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import com.hanaahany.task.model.response.search.SearchResponse
import retrofit2.Response

interface RemoteSource {
    suspend fun getAllRepo(): Response<AllRepoResponse>
    suspend fun getRepo(login:String,name:String): Response<RepoDetailsResponse>
    suspend fun getIssues(login:String,name:String): Response<IssuesResponse>
    suspend fun searchRepo(query:String):Response<SearchResponse>

}