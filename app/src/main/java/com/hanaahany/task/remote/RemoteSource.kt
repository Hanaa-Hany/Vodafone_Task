package com.hanaahany.task.remote

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import retrofit2.Response

interface RemoteSource {
    suspend fun getAllRepo(): Response<AllRepoResponse>
    suspend fun getRepo(login:String,name:String): Response<RepoDetailsResponse>

}