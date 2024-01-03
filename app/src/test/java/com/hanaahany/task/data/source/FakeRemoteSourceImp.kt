package com.hanaahany.task.data.source

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.issues.IssuesResponse
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import com.hanaahany.task.model.response.search.SearchResponse
import com.hanaahany.task.remote.RemoteSource
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody

import retrofit2.Response

class FakeRemoteSourceImp : RemoteSource {
    private var shouldReturnSuccess: Boolean = true

    fun setShouldReturnSuccess(shouldReturnSuccess: Boolean) {
        this.shouldReturnSuccess = shouldReturnSuccess
    }


    override suspend fun getAllRepo(): Response<AllRepoResponse> {

        return if (shouldReturnSuccess) {
            Response.success(FakeData.fakeAllRepoResponse())
        } else {
            Response.error(400, ResponseBody.create("application/json".toMediaTypeOrNull(), "Fake error response"))
        }


    }

    override suspend fun getRepo(login: String, name: String): Response<RepoDetailsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getIssues(login: String, name: String): Response<IssuesResponse> {
        return if (shouldReturnSuccess) {
            return Response.success(FakeData.fakeIssuesResponse())
        } else {
            Response.error(400, ResponseBody.create("application/json".toMediaTypeOrNull(), "Fake error response"))
        }
    }

    override suspend fun searchRepo(query: String): Response<SearchResponse> {
        TODO("Not yet implemented")
    }
}