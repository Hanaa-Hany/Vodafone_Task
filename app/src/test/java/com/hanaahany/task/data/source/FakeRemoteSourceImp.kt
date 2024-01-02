package com.hanaahany.task.data.source

import com.hanaahany.task.model.response.allrepo.AllRepoResponse
import com.hanaahany.task.model.response.issues.IssuesResponse
import com.hanaahany.task.model.response.repodetails.RepoDetailsResponse
import com.hanaahany.task.model.ui.allrepo.AllRepoItem
import com.hanaahany.task.model.ui.issues.IssuesItem
import com.hanaahany.task.model.ui.repodetails.RepoDetails
import com.hanaahany.task.remote.ApiState
import com.hanaahany.task.remote.RemoteSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class FakeRemoteSourceImp : RemoteSource {
    override suspend fun getAllRepo(): Response<AllRepoResponse> {
        return Response.success(FakeData.fakeAllRepoResponse())
    }

    override suspend fun getRepo(login: String, name: String): Response<RepoDetailsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getIssues(login: String, name: String): Response<IssuesResponse> {
        TODO("Not yet implemented")
    }
}