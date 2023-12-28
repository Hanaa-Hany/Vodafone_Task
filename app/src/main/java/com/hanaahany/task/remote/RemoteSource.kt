package com.hanaahany.task.remote

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import retrofit2.Response

interface RemoteSource {
    suspend fun getAllRepo(): Response<AllRepoResponse>
}